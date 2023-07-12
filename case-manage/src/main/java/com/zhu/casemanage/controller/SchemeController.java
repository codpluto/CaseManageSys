package com.zhu.casemanage.controller;

import com.zhu.casemanage.pojo.SchemePojo;
import com.zhu.casemanage.pojo.TDSchemePojo;
import com.zhu.casemanage.service.SchemeServiceImpl;
import com.zhu.casemanage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/platform/cmScheme")
public class SchemeController {

    @Autowired
    private SchemeServiceImpl schemeService;


    /*
     * 根据病例号获取其所有的治疗方案（列表）
     * */
//    @RequestMapping(value = "/file/{caseNumber}",method = RequestMethod.GET)
//    public Result getSchemeInfoByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
//        return new Result();
//    }

//    /*
//     * 根据病例号获取其所有的批复记录（列表）
//     * */
//    @RequestMapping(value = "/audit/list/caseId/{caseNumber}/recNum/{recNum}/seqNum/{seqNum}",method = RequestMethod.GET)
//    public Result getAuditListByCaseNumber(@PathVariable("caseNumber") Long caseNumber,@PathVariable("recNum") String recNum,@PathVariable("seqNum") String seqNum) {
//        return new Result();
//    }
//
//    /*
//     * 判断指定病历号的3D设计方案是否已审核过（true为未审核）
//     * */
//    @RequestMapping(value = "/scheme/judge/{caseNumber}",method = RequestMethod.GET)
//    public Result getAuditListByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
//        return new Result();
//    }
//
//    /*
//     * 根据病例号获取其所有的治疗方案（数组）,优化版
//     * */
//    @RequestMapping(value = "/file_new/{caseNumber}",method = RequestMethod.GET)
//    public Result getNewSchemeInfoByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
//        return new Result();
//    }

    /*
    * 新增治疗方案（新增病例界面）
    * */
    @RequestMapping(value = "/scheme/addScheme",method = RequestMethod.POST)
    public Result addScheme(@RequestBody SchemePojo newScheme){
        schemeService.addScheme(newScheme);
        return Result.success();
    }


    /*
    * 根据病例号获取3d方案列表
    * */
    @RequestMapping(value = "/3dScheme/list/{caseNumber}",method = RequestMethod.GET)
    public Result get3dSchemeList(@PathVariable("caseNumber") Long caseNumber){
        List<TDSchemePojo> tdSchemeList = schemeService.get3dSchemeList(caseNumber);
        return Result.success(tdSchemeList);
    }



    /*
    * 新增3D方案
    * */
    @RequestMapping(value = "/3dScheme",method = RequestMethod.POST)
    public Result add3dScheme(@RequestBody TDSchemePojo tdSchemePojo){
        schemeService.add3dScheme(tdSchemePojo);
        return Result.success();
    }

    /*
    * 修改3D方案
    * */
//    @RequestMapping(value = "/3dScheme",method = RequestMethod.PUT)
//    public Result update3dScheme()

}

