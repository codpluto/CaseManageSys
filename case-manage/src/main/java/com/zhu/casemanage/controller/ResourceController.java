package com.zhu.casemanage.controller;

import com.zhu.casemanage.utils.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/resource")
@RestController
public class ResourceController {

    /*
    * 返回公司信息(logo,officialWebsite,softIcon)
    * */
    @RequestMapping(value = "/org")
    public Result showOrgInfo() {
        return new Result();
    }

    /*
     * 返回公司信息（更全面）
     * */
    @RequestMapping(value = "/org/login")
    public Result showOrgLoginInfo() {
        return new Result();
    }

}
