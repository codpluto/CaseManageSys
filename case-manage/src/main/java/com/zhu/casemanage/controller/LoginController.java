package com.zhu.casemanage.controller;

import com.zhu.casemanage.constant.UserConstant;
import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.pojo.UserPojo;
import com.zhu.casemanage.service.UserServiceImpl;
import com.zhu.casemanage.utils.Result;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    private RedisTemplate redisTemplate;

    /*
    * 登录
    * */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public Result login(@RequestBody UserPojo user) {
        String token = userService.login(user.getAccount(), user.getPassword());
        return Result.success(token);
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

}
