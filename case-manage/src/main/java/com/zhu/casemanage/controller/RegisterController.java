package com.zhu.casemanage.controller;

import com.zhu.casemanage.pojo.UserPojo;
import com.zhu.casemanage.service.UserServiceImpl;
import com.zhu.casemanage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping(value = "/")
public class RegisterController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(@RequestBody UserPojo newUser){
        userService.addUser(newUser);
        return Result.success();
    }

}
