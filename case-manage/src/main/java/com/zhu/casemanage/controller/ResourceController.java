package com.zhu.casemanage.controller;

import com.zhu.casemanage.utils.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/resource")
@RestController
public class ResourceController {

    /*
    * 查询部分公司信息
    * */
    @RequestMapping(value = "/org")
    public Result showOrgInfo() {
        return new Result();
    }

    /*
     * 查询更全面的公司信息
     * */
    @RequestMapping(value = "/org/login")
    public Result showOrgLoginInfo() {
        return new Result();
    }

}
