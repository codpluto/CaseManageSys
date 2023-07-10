package com.zhu.casemanage.controller;

import com.zhu.casemanage.pojo.AddressPojo;
import com.zhu.casemanage.service.AddressServiceImpl;
import com.zhu.casemanage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/platform")
public class AddressController {
    @Autowired
    AddressServiceImpl addressService;

    /*
     * 返回当前登录用户SysUser的邮寄地址信息
     * */
    @RequestMapping(value = "/cmAddress",method = RequestMethod.GET)
    public Result getAddressList(@RequestParam("userId") int userId) {
        List<AddressPojo> userAddressList = null;
        try {
            userAddressList = addressService.findUserAddressList(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Result.success(userAddressList);
    }

    /*
     * 新增一个邮寄地址
     * */
    @RequestMapping(value = "/cmAddress",method = RequestMethod.POST)
    public Result addAddress(@RequestBody AddressPojo newAddress) {
        try {
            addressService.addAddress(newAddress);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Result.success();
    }

    /*
     * 根据主治医生id获取其全部的邮寄地址
     * */
//    @RequestMapping(value = "/cmAddress/doctor/{id}",method = RequestMethod.POST)
//    public Result getAllAddressById(@PathVariable("id") int id) {
//        return new Result();
//    }

    /*
     * 根据地址id获取该病例号的主治医生的地址信息
     * */
    @RequestMapping(value = "/cmAddress/{caseNumber}/{id}",method = RequestMethod.POST)
    public Result getAddressByCaseNumberAndId(@PathVariable("caseNumber") Long caseNumber,@PathVariable("id") int id) {
        return new Result();
    }



}
