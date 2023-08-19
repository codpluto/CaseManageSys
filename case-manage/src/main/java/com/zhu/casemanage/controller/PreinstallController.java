package com.zhu.casemanage.controller;


import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.zhu.casemanage.pojo.CasePojo;
import com.zhu.casemanage.pojo.PreferPojo;
import com.zhu.casemanage.service.PreferServiceImpl;
import com.zhu.casemanage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(servletRequestAttributes, "header 获取异常");
        // 获取请求头中的商户id
        String token = servletRequestAttributes.getRequest().getHeader("token");
        Assert.notNull(token, "token获取失败");
        preferService.addPrefer(newPrefer,token);
        return Result.success();
    }

    /*
     * 更新预设偏好设定
     * */
    @RequestMapping(value = "/cmPreinstall",method = RequestMethod.PUT)
    public Result commitPreinstall(@RequestBody PreferPojo newPrefer) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(servletRequestAttributes, "header 获取异常");
        // 获取请求头中的商户id
        String token = servletRequestAttributes.getRequest().getHeader("token");
        Assert.notNull(token, "token获取失败");
        preferService.updatePrefer(newPrefer,token);
        return Result.success();
    }

    /*
     * 设置预设偏好设定的偏好名称
     * */
    @RequestMapping(value = "/cmPreinstall/valid",method = RequestMethod.POST)
    public Result addPreinstallName(@RequestParam Integer preferId,
                                    @RequestParam String preinstall) {
        preferService.setPreinstallName(preferId,preinstall);
        return Result.success();
    }

    /*
     * 返回当前登录用户SysUser的偏好设定信息（只返回id和name字段）
     * */
    @RequestMapping(value = "/cmPreinstall/list",method = RequestMethod.GET)
    public Result getPreinstallList() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(servletRequestAttributes, "header 获取异常");
        // 获取请求头中的商户id
        String token = servletRequestAttributes.getRequest().getHeader("token");
        Assert.notNull(token, "token获取失败");

        List<PreferPojo> perferList = preferService.getPerferListByToken(token);
        List<Map<String, Object>> list = new ArrayList<>();
        for (PreferPojo prefer:
             perferList) {
            Map<String, Object> map = new HashMap<>();
            map.put("preferId",prefer.getPreferId());
            map.put("preinstall",prefer.getPreinstall());
            list.add(map);
        }
        return Result.success(list);
    }

    /*
     * 根据偏好设定id返回该偏好设定的设定情况
     * */
    @RequestMapping(value = "/cmPreinstall/{preferId}",method = RequestMethod.GET)
    public Result getPreinstallInfoById(@PathVariable("preferId") Integer preferId) {
        PreferPojo preferById = preferService.getPreferById(preferId);
        return Result.success(preferById);
    }

    /*
     * 根据偏好设定id删除该条偏好设定
     * */
    @RequestMapping(value = "/cmPreinstall/{id}",method = RequestMethod.DELETE)
    public Result deletePreinstallInfoById(@PathVariable("id") Integer id) {
        preferService.delPrefer(id);
        return Result.success();
    }



}
