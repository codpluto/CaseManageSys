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
        List<AddressPojo> userAddressList = addressService.findUserAddressList(userId);
        if (userAddressList == null ) {
            return Result.failed("查询失败");
        }
//        if (userAddressList.size() == 0)
//            return Result.failGetString("查询失败");
        return Result.success(userAddressList);
    }

    /*
     * 新增一个邮寄地址
     * */
    @RequestMapping(value = "/cmAddress",method = RequestMethod.POST)
    public Result addAddress(@RequestBody AddressPojo newAddress) {
        addressService.addAddress(newAddress);
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
     * 获取该病例号的主治医生的地址信息，根据地址id获取
     * */
    @RequestMapping(value = "/cmAddress/{caseNumber}/{id}",method = RequestMethod.POST)
    public Result getAddressByCaseNumberAndId(@PathVariable("caseNumber") String caseNumber,@PathVariable("id") int id) {
        return new Result();
    }

}
