package com.zhu.casemanage.controller;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.zhu.casemanage.pojo.AddressPojo;
import com.zhu.casemanage.service.AddressServiceImpl;
import com.zhu.casemanage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
    public Result getAddressList() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(servletRequestAttributes, "header 获取异常");
        // 获取请求头中的商户id
        String token = servletRequestAttributes.getRequest().getHeader("token");
        Assert.notNull(token, "token获取失败");
        List<AddressPojo> sysUserAddressList = addressService.getSysUserAddressList(token);
        return Result.success(sysUserAddressList);
    }

    /*
     * 新增当前用户的一个邮寄地址
     * */
    @RequestMapping(value = "/cmAddress",method = RequestMethod.POST)
    public Result addAddress(@RequestBody AddressPojo newAddress) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(servletRequestAttributes, "header 获取异常");
        // 获取请求头中的商户id
        String token = servletRequestAttributes.getRequest().getHeader("token");
        Assert.notNull(token, "token获取失败");
        addressService.addAddress(newAddress,token);

        return Result.success();
    }

    /*
     * 根据主治医生id获取其全部的邮寄地址
     * */
    @RequestMapping(value = "/cmAddress/doctor/{id}",method = RequestMethod.GET)
    public Result getAllAddressById(@PathVariable("id") int id) {
        List<AddressPojo> addressList = null;
        try {
            addressList = addressService.findUserAddressList(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Result.success(addressList);
    }

//    /*
//     * 根据地址id获取该病例号的主治医生的地址信息
//     * */
//    @RequestMapping(value = "/cmAddress/{caseNumber}/{id}",method = RequestMethod.POST)
//    public Result getAddressByCaseNumberAndId(@PathVariable("caseNumber") Long caseNumber,@PathVariable("id") int id) {
//        return new Result();
//    }



}
