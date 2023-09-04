package com.zhu.casemanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zhu.casemanage.constant.UserConstant;
import com.zhu.casemanage.dao.UserDao;
import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.pojo.UserPojo;
import com.zhu.casemanage.service.UserServiceImpl;
import com.zhu.casemanage.utils.MyUtils;
import com.zhu.casemanage.utils.Result;
import com.zhu.casemanage.utils.SMSUtils;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private UserDao userDao;

    /*
    * 登录
    * */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public Result login(@RequestBody UserPojo user) {
        Map<String,Object> map = userService.login(user.getAccount(), user.getPassword());
//        String token = userService.login(user.getAccount(), user.getPassword());
        return Result.success(map);
    }

    /*
     * 获得token
     * */
//    @RequestMapping(value = "/{token}", method = RequestMethod.POST)
//    public Result sysUserLogin(@PathVariable("token") String token) {
//        return new Result();
//    }

    /*
     * 注销token
     * */
    @RequestMapping(value = "/token/{account}", method = RequestMethod.DELETE)
    public Result sysUserLogout(@PathVariable("account") String account) {
        redisTemplate.delete(UserConstant.getTokenKey(account));
        return Result.success();
    }

    /**
     * 根据Id查询用户信息
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public Result getUserInfo(@RequestParam("userId") int userId){
        UserPojo userInfoById = userService.getUserInfoById(userId);
        return Result.success(userInfoById);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(@RequestBody UserPojo newUser){
        userService.addUser(newUser);
        return Result.success();
    }

    /*
    * 验证手机号或账号是否存在（忘记密码时第一步用到）
    * */
    @RequestMapping(value = "/forgetPassword/isValid",method = RequestMethod.GET)
    public Result userIsValid(@RequestParam(value = "phone",defaultValue = "",required = false) String phone,
                              @RequestParam(value = "account",defaultValue = "",required = false) String account){
        if (phone.length() > 0){
            UserPojo userPojo = userDao.selectOne(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getUserPhone, phone));
            if (userPojo == null){
                Map<String,Object> map = new HashMap<>();
                map.put("isValid",false);
                return Result.success(map);
            } else {
                Map<String,Object> map = new HashMap<>();
                map.put("phone",MyUtils.hidePhoneNo(phone));
                map.put("isValid",true);
                return Result.success(map);
            }
        }
        UserPojo userPojo = userDao.selectOne(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getAccount, account));
        if (userPojo == null){
            Map<String,Object> map = new HashMap<>();
            map.put("isValid",false);
            return Result.success(map);
        } else {
            Map<String,Object> map = new HashMap<>();
            map.put("phone",MyUtils.hidePhoneNo(userPojo.getUserPhone()));
            map.put("isValid",true);
            return Result.success(map);
        }
    }


    /*
    * 发送手机验证码(修改密码)
    * */
    @RequestMapping(value = "/forgetPassword/code",method = RequestMethod.GET)
    public Result getCode(@RequestParam(value = "phone",defaultValue = "",required = false) String phone,
                          @RequestParam(value = "account",defaultValue = "",required = false) String account){
        String targetPhone;
        if (phone.length() == 0){
            targetPhone = userService.getUserPhoneByAccount(account);
        } else {
            targetPhone = phone;
        }

        String codeKey = "code:forgetPassword:" + targetPhone;
        // 发送前先看下我们是否已经缓存了验证码
        String yzm = (String) redisTemplate.opsForValue().get(codeKey);
        // 判断是否存在
        if (yzm == null){
            // 生成六位数验证码
            int authNum = new Random().nextInt(899999) + 100000;
            String authCode = String.valueOf(authNum);
            // 不存在，我们发送验证码给用户
            SMSUtils.sendMessage(targetPhone,authCode);
            // 存入redis中，设置有效期为5分钟
            redisTemplate.opsForValue().set(codeKey, authCode, 5, TimeUnit.MINUTES);
            return Result.success();
        }
        // 存在，直接返回，不再发送验证码~
        throw new BusinessException("请勿重复发送验证码");
    }

    /*
    * 验证验证码
    * */
    @RequestMapping(value = "/forgetPassword/checkCode",method = RequestMethod.POST)
    public Result checkCode(@RequestBody Map<String,Object> map){
        String code = (String) map.get("code");
        String account = (String) map.get("account");
        String phone = (String) map.get("phone");
        String targetPhone;
        if (phone == null){
            targetPhone = userService.getUserPhoneByAccount(account);
        } else {
            targetPhone = phone;
        }
        String codeKey = "code:forgetPassword:" + targetPhone;
        String realCode = (String) redisTemplate.opsForValue().get(codeKey);
        if (realCode == null || !realCode.equals(code)){
            return Result.success(false);
        }
        return Result.success(true);
    }

    /*
    * 修改密码
    * */
    @RequestMapping(value = "/forgetPassword/updatePassword",method = RequestMethod.PUT)
    public Result updatePassword(@RequestBody Map<String,Object> map){
        String newPassword = (String) map.get("newPassword");
        String phone = (String) map.get("phone");
        String account = (String) map.get("account");
        if (phone != null){
            LambdaUpdateWrapper<UserPojo> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(UserPojo::getUserPhone,phone)
                    .set(UserPojo::getPassword,newPassword);
            if (userDao.update(null,wrapper) == 0){
                throw new BusinessException("密码修改失败");
            }
            return Result.success();
        }
        LambdaUpdateWrapper<UserPojo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(UserPojo::getAccount,account)
                .set(UserPojo::getPassword,newPassword);
        if (userDao.update(null,wrapper) == 0){
            throw new BusinessException("密码修改失败");
        }
        return Result.success();
    }

}
