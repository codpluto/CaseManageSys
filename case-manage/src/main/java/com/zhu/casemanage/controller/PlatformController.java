package com.zhu.casemanage.controller;


import com.aeert.jfilter.annotation.MoreSerializeField;
import com.aeert.jfilter.annotation.SerializeField;
import com.zhu.casemanage.pojo.CasePojo;
import com.zhu.casemanage.pojo.UserPojo;
import com.zhu.casemanage.service.UserServiceImpl;
import com.zhu.casemanage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/platform")
public class PlatformController {

    @Autowired
    private UserServiceImpl userService;

    /*
     * 查询登录平台的用户信息
     * */
    @RequestMapping(value = "/sysUser",method = RequestMethod.GET)
    public Result getSysUser() {
        return new Result();
    }

    /*
     * 查询平台的所有医生用户信息
     * */
    @RequestMapping(value = "/sysUser/doctor",method = RequestMethod.GET)
    @MoreSerializeField({
            @SerializeField(clazz = UserPojo.class, includes = {"userId","userName"}),
    })
    public Result getSysUserDoctor() {
        List<UserPojo> userList = userService.getUserListByType(1);
        return Result.success(userList);
    }

    /*
     * 查询平台的所有技工用户信息
     * */
    @RequestMapping(value = "/sysUser/mechanic",method = RequestMethod.GET)
    public Result getSysUserMechanic() {
        return new Result();
    }

    /*
     * 查询平台的所有专家用户信息
     * */
    @RequestMapping(value = "/sysUser/specialist",method = RequestMethod.GET)
    public Result getSysUserSpecialist() {
        return new Result();
    }

    /*
     * 查询平台的所有主管用户信息
     * */
    @RequestMapping(value = "/sysUser/supervisor",method = RequestMethod.GET)
    public Result getSysUserSupervisor() {
        return new Result();
    }

//    /*
//     *
//     * */
//    @RequestMapping(value = "/cmAddress/button",method = RequestMethod.POST)
//    public Result name() {
//        return new Result();
//    }

}
