package com.zhu.casemanage.controller;

import com.zhu.casemanage.pojo.PreferPojo;
import com.zhu.casemanage.service.PreferServiceImpl;
import com.zhu.casemanage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/platform")
public class PreinstallController {
    @Autowired
    private PreferServiceImpl preferService;

    /*
     * 新增预设偏好设定（仅新增会用到）
     * */
    @RequestMapping(value = "/cmPreinstall",method = RequestMethod.POST)
    public Result addPreinstall(@RequestBody PreferPojo newPrefer) {
        preferService.addPrefer(newPrefer);
        return Result.success();
    }

    /*
     * 更新预设偏好设定
     * */
    @RequestMapping(value = "/cmPreinstall",method = RequestMethod.PUT)
    public Result commitPreinstall(@RequestBody PreferPojo newPrefer) {
        preferService.updatePrefer(newPrefer);
        return Result.success();
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
    public Result getPreinstallList(@RequestParam("userId") int userId) {
        List<PreferPojo> perferList = preferService.findPerferList(userId);
        return Result.success(perferList);
    }

    /*
     * 根据偏好设定id返回该偏好设定的设定情况
     * */
    @RequestMapping(value = "/cmPreinstall/{id}",method = RequestMethod.GET)
    public Result getPreinstallInfoById(@PathVariable("id") int id) {
        PreferPojo preferById = preferService.findPreferById(id);
        return Result.success(preferById);
    }

    /*
     * 根据偏好设定id删除该条偏好设定
     * */
    @RequestMapping(value = "/cmPreinstall/{id}",method = RequestMethod.DELETE)
    public Result deletePreinstallInfoById(@PathVariable("id") int id) {
        preferService.delPrefer(id);
        return Result.success();
    }



}
