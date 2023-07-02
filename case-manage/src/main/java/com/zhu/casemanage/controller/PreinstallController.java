//package com.zhu.casemanage.controller;
//
//import com.zhu.casemanage.utils.Result;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(value = "/platform")
//public class PreinstallController {
//
//    /*
//     * 新增预设偏好设定（仅新增会用到）
//     * */
//    @RequestMapping(value = "/cmPreinstall",method = RequestMethod.POST)
//    public Result addPreinstall() {
//        return new Result();
//    }
//
//    /*
//     * 提交预设偏好设定(新增、修改都会用到)
//     * */
//    @RequestMapping(value = "/cmPreinstall",method = RequestMethod.PUT)
//    public Result commitPreinstall() {
//        return new Result();
//    }
//
//    /*
//     * 设置预设偏好设定的偏好名称
//     * */
//    @RequestMapping(value = "/cmPreinstall/valid",method = RequestMethod.POST)
//    public Result addPreinstallName() {
//        return new Result();
//    }
//
//    /*
//     * 返回当前登录用户SysUser的偏好设定信息
//     * */
//    @RequestMapping(value = "/cmPreinstall/list",method = RequestMethod.GET)
//    public Result getPreinstallList() {
//        return new Result();
//    }
//
//    /*
//     * 根据偏好设定id返回该偏好设定的设定情况
//     * */
//    @RequestMapping(value = "/cmPreinstall/{id}",method = RequestMethod.GET)
//    public Result getPreinstallInfoById(@PathVariable("id") String id) {
//        return new Result();
//    }
//
//    /*
//     * 根据偏好设定id删除该条偏好设定
//     * */
//    @RequestMapping(value = "/cmPreinstall/{id}",method = RequestMethod.DELETE)
//    public Result deletePreinstallInfoById(@PathVariable("id") String id) {
//        return new Result();
//    }
//
//}
