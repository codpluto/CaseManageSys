package com.zhu.casemanage.controller;

import com.zhu.casemanage.utils.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/platform")
public class PlatformController {

    /*
     * 返回登录平台的用户信息
     * */
    @RequestMapping(value = "/sysUser",method = RequestMethod.GET)
    public Result getSysUser() {
        return new Result();
    }

    /*
     * 返回平台的所有医生用户信息
     * */
    @RequestMapping(value = "/sysUser/doctor",method = RequestMethod.GET)
    public Result getSysUserDoctor() {
        return new Result();
    }

    /*
     * 返回平台的所有技工用户信息
     * */
    @RequestMapping(value = "/sysUser/mechanic",method = RequestMethod.GET)
    public Result getSysUserMechanic() {
        return new Result();
    }

    /*
     * 返回平台的所有专家用户信息
     * */
    @RequestMapping(value = "/sysUser/specialist",method = RequestMethod.GET)
    public Result getSysUserSpecialist() {
        return new Result();
    }

    /*
     * 返回平台的所有主管用户信息
     * */
    @RequestMapping(value = "/sysUser/supervisor",method = RequestMethod.GET)
    public Result getSysUserSupervisor() {
        return new Result();
    }

    /*
     * 暂存患者信息
     * */
    @RequestMapping(value = "/cmCaseInfo/record",method = RequestMethod.POST)
    public Result recordCaseInfo() {
        return new Result();
    }

    /*
     * 返回全部病例的第pageNum页（默认状态为全部）
     * */
    @RequestMapping(value = "/cmCaseInfo/{pageNum}/{navigatePages}",method = RequestMethod.GET)
    public Result showCaseInfo(@PathVariable("pageNum") int pageNum,@PathVariable("navigatePages") int navigatePages) {
        return new Result();
    }

    /*
     * 返回按所选状态(selectStatus)的全部病例的第pageNum页
     * */
    @RequestMapping(value = "/cmCaseInfo/dispose/{pageNum}/{navigatePages}",method = RequestMethod.GET)
    public Result showCaseInfoBySelectStatus(@PathVariable("pageNum") int pageNum,@PathVariable("selectStatus") int selectStatus ,@PathVariable("navigatePages") int navigatePages) {
        return new Result();
    }

    /*
     * 返回按关键字搜索(selectParam)的全部病例的第pageNum页
     * */
    @RequestMapping(value = "/cmCaseInfo/dispose/{pageNum}/{navigatePages}",method = RequestMethod.GET)
    public Result showCaseInfoBySelectParam(@PathVariable("pageNum") int pageNum,@PathVariable("selectParam") int selectStatus ,@PathVariable("navigatePages") int navigatePages) {
        return new Result();
    }

    /*
     * 返回待处理病例的第pageNum页（默认状态为全部）
     * */
    @RequestMapping(value = "/cmCaseInfo/dispose/{pageNum}/{navigatePages}",method = RequestMethod.GET)
    public Result showDisposeCaseInfo(@PathVariable("pageNum") int pageNum,@PathVariable("navigatePages") int navigatePages) {
        return new Result();
    }

    /*
     * 返回按所选状态的待处理病例的第pageNum页
     * */
    @RequestMapping(value = "/cmCaseInfo/dispose/{pageNum}/{navigatePages}",method = RequestMethod.GET)
    public Result showDisposeCaseInfoBySelectStatus(@PathVariable("pageNum") int pageNum,@PathVariable("selectStatus") int selectStatus,@PathVariable("navigatePages") int navigatePages) {
        return new Result();
    }

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
     * 根据主治医生id获取其全部的收货地址
     * */
    @RequestMapping(value = "/cmAddress/doctor/{id}",method = RequestMethod.POST)
    public Result getDoctorAddressById(@PathVariable("id") int id) {
        return new Result();
    }

    /*
     * 新增预设偏好设定（仅新增会用到）
     * */
    @RequestMapping(value = "/cmPreinstall",method = RequestMethod.POST)
    public Result addPreinstall() {
        return new Result();
    }

    /*
     * 提交预设偏好设定(新增、修改都会用到)
     * */
    @RequestMapping(value = "/cmPreinstall",method = RequestMethod.PUT)
    public Result commitPreinstall() {
        return new Result();
    }

    /*
     * 设置预设偏好设定的偏好名称
     * */
    @RequestMapping(value = "/cmPreinstall/valid",method = RequestMethod.POST)
    public Result addPreinstallName() {
        return new Result();
    }

    /*
     * 返回当前登录用户SysUser的偏好设定信息
     * */
    @RequestMapping(value = "/cmPreinstall/list",method = RequestMethod.GET)
    public Result getPreinstallList() {
        return new Result();
    }

    /*
     * 根据偏好设定id返回该偏好设定的设定情况
     * */
    @RequestMapping(value = "/cmPreinstall/{id}",method = RequestMethod.GET)
    public Result getPreinstallInfoById(@PathVariable("id") String id) {
        return new Result();
    }

    /*
     * 根据偏好设定id删除该条偏好设定
     * */
    @RequestMapping(value = "/cmPreinstall/{id}",method = RequestMethod.DELETE)
    public Result deletePreinstallInfoById(@PathVariable("id") String id) {
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
