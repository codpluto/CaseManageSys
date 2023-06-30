package com.zhu.casemanage.controller;

import com.zhu.casemanage.utils.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    /*
    * 登录
    * */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public Result login() {
        return new Result();
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

}
