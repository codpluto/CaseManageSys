package com.zhu.casemanage.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.zhu.casemanage.constant.UserConstant;
import com.zhu.casemanage.dao.UserDao;
import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.pojo.UserPojo;
import com.zhu.casemanage.utils.JwtUtil;
import com.zhu.casemanage.utils.RedisUtil;
import com.zhu.casemanage.utils.Result;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl {
    @Autowired
    private UserDao userDao;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RedisUtil redisUtil;


    //登录
    public UserPojo findUserByAccount(String account){
        UserPojo user = userDao.selectOne(new QueryWrapper<UserPojo>().eq("account", account));
        return user;
    }

    /*
    * 根据token查询用户信息
    * */
    public UserPojo getUserByToken(String token){
        String account = jwtUtil.parseToken(token);
        String tokenCache = (String) redisUtil.get(UserConstant.getTokenKey(account));
        if (tokenCache == null){
            throw new BusinessException("token已过期");
        } else if (!tokenCache.equals(token)){
            throw new BusinessException("token已过期");
        }
        UserPojo userPojo = userDao.selectOne(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getAccount, account));
        if (userPojo == null){
            throw new BusinessException("用户不存在");
        }
        return userPojo;
    }


    public void addUser(UserPojo newUser){
        if (userDao.selectOne(new QueryWrapper<UserPojo>().eq("account",newUser.getAccount())) != null){
            throw new BusinessException("账号已注册");
        }
        userDao.insert(newUser);
    }

    public Map<String,Object> login(String account,String password){
        UserPojo user = this.findUserByAccount(account);
        if (user == null){
//            return Result.failed("账号或密码错误");
            throw new BusinessException("用户不存在");
        }

        if (!user.getPassword().equals(password)){
            throw new BusinessException("密码错误");
        }
        //登录成功，生产token
        log.info("login success,account={}",account);
        String token = jwtUtil.createToken(user.getAccount());
        //redis存token,30min有效期
//        redisUtil.setMinute(UserConstant.getTokenKey(user.getAccount()),token,30);
        redisUtil.set(UserConstant.getTokenKey(user.getAccount()),token,TimeUnit.MINUTES.toSeconds(30));
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("userId",user.getUserId());
        return map;
    }

    /*
     *
     * 根据查询用户信息
     */
    public UserPojo getUserInfoById(int userId){
        UserPojo userPojo = userDao.selectById(userId);
        if (userPojo == null){
            throw new BusinessException("用户不存在");
        }
        return userPojo;
    }

    /*
    * 删除用户
    * */
    public void delUserByAccount(String account){
        if (userDao.delete(new QueryWrapper<UserPojo>().eq("account",account)) == 0){
            throw new BusinessException("用户不存在,删除失败");
        }
    }

    /*
     * 获取某种类型的用户列表
     */
    public List<UserPojo> getUserListByType(int type){
        return userDao.selectList(new QueryWrapper<UserPojo>().eq("user_type", type));
    }

    /*
    * 根据用户id返回用户类型
    * */
    public int getUserTypeById(int userId){
        return userDao.selectById(userId).getUserType();
    }

}
