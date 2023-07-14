package com.zhu.casemanage.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhu.casemanage.pojo.UserPojo;
import com.zhu.casemanage.service.UserServiceImpl;
import com.zhu.casemanage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/platform")
public class PlatformController {

    @Autowired
    private UserServiceImpl userService;

    /*
     * 查询登录平台的用户信息
     * */
    @RequestMapping(value = "/sysUser",method = RequestMethod.GET)
    public Result getSysUser(@RequestParam String account) {
        UserPojo user = userService.findUserByAccount(account);
        return Result.success(user);
    }

    /*
     * 查询平台的所有医生用户信息
     * */
    @RequestMapping(value = "/sysUser/doctor",method = RequestMethod.GET)
    public Result getSysUserDoctor() {
        List<UserPojo> userList = userService.getUserListByType(1);
        List<Map<String, Object>> list = new ArrayList<>();
        for (UserPojo user:
             userList) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId",user.getUserId());
            map.put("userName",user.getUserName());
            list.add(map);
        }
        return Result.success(list);
    }

    /*
     * 查询平台的所有专家用户信息
     * */
    @RequestMapping(value = "/sysUser/specialist",method = RequestMethod.GET)
    public Result getSysUserSpecialist() {
        List<UserPojo> userList = userService.getUserListByType(2);
        List<Map<String, Object>> list = new ArrayList<>();
        for (UserPojo user:
                userList) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId",user.getUserId());
            map.put("userName",user.getUserName());
            list.add(map);
        }
        return Result.success(list);
    }

    /*
     * 查询平台的所有技工用户信息
     * */
    @RequestMapping(value = "/sysUser/mechanic",method = RequestMethod.GET)
    public Result getSysUserMechanic() {
        List<UserPojo> userList = userService.getUserListByType(3);
        List<Map<String, Object>> list = new ArrayList<>();
        for (UserPojo user:
                userList) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId",user.getUserId());
            map.put("userName",user.getUserName());
            list.add(map);
        }
        return Result.success(list);
    }

    /*
     * 查询平台的所有主管用户信息
     * */
    @RequestMapping(value = "/sysUser/supervisor",method = RequestMethod.GET)
    public Result getSysUserSupervisor() {
        List<UserPojo> userList = userService.getUserListByType(4);
        List<Map<String, Object>> list = new ArrayList<>();
        for (UserPojo user:
                userList) {
            Map<String, Object> map = new HashMap<>();
            map.put("userId",user.getUserId());
            map.put("userName",user.getUserName());
            list.add(map);
        }
        return Result.success(list);
    }


}
