package com.zhu.casemanage.controller;

import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.pojo.UserPojo;
import com.zhu.casemanage.service.UserServiceImpl;
import com.zhu.casemanage.utils.Result;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    UserServiceImpl userService;

    /*
    * 登录
    * */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public Result login(@RequestParam("account") String account,
                        @RequestParam("password") String password) {
        String token = userService.login(account, password);
        return Result.success(token);
    }

    /*
     * 获得token
     * */
    @RequestMapping(value = "/{token}", method = RequestMethod.POST)
    public Result sysUserLogin(@PathVariable("token") String token) {
        return new Result();
    }

    /*
     * 注销token
     * */
    @RequestMapping(value = "/{token}", method = RequestMethod.PUT)
    public Result sysUserLogout(@PathVariable("token") String token) {
        return new Result();
    }

//    /**
//     *注册
//     */
//    @RequestMapping(value = "")

    /**
     * 根据Id获取用户信息
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public Result getUserInfo(@RequestParam("userId") int userId){
        UserPojo userInfoById = userService.getUserInfoById(userId);
        return Result.success(userInfoById);
    }

}
