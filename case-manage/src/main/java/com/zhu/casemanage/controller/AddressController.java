package com.zhu.casemanage.controller;

import com.zhu.casemanage.utils.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/platform")
public class AddressController {

    /*
     * 返回当前登录用户SysUser的邮寄地址信息
     * */
    @RequestMapping(value = "/cmAddress",method = RequestMethod.GET)
    public Result getAddressList() {
        return new Result();
    }

    /*
     * 新增一个邮寄地址
     * */
    @RequestMapping(value = "/cmAddress",method = RequestMethod.POST)
    public Result addAddress() {
        return new Result();
    }

    /*
     * 根据主治医生id获取其全部的邮寄地址
     * */
    @RequestMapping(value = "/cmAddress/doctor/{id}",method = RequestMethod.POST)
    public Result getAllAddressById(@PathVariable("id") int id) {
        return new Result();
    }

    /*
     * 获取该病例号的主治医生的地址信息，根据地址id获取
     * */
    @RequestMapping(value = "/cmAddress/{caseNumber}/{id}",method = RequestMethod.POST)
    public Result getAddressByCaseNumberAndId(@PathVariable("caseNumber") String caseNumber,@PathVariable("id") int id) {
        return new Result();
    }

}
