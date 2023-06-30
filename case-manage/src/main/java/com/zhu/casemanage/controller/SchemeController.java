package com.zhu.casemanage.controller;

import com.zhu.casemanage.utils.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/platform/cmScheme")
public class SchemeController {

    /*
     * 根据病例号获取其所有的治疗方案（列表）
     * */
    @RequestMapping(value = "/file/{caseNumber}",method = RequestMethod.GET)
    public Result getSchemeInfoByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
        return new Result();
    }

    /*
     * 根据病例号获取其所有的批复记录（列表）
     * */
    @RequestMapping(value = "/audit/list/caseId/{caseNumber}/recNum/{recNum}/seqNum/{seqNum}",method = RequestMethod.GET)
    public Result getAuditListByCaseNumber(@PathVariable("caseNumber") String caseNumber,@PathVariable("recNum") String recNum,@PathVariable("seqNum") String seqNum) {
        return new Result();
    }

    /*
     * 判断指定病历号的3D设计方案是否已审核过（true为未审核）
     * */
    @RequestMapping(value = "scheme/judge/{caseNumber}",method = RequestMethod.GET)
    public Result getAuditListByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
        return new Result();
    }

    /*
     * 根据病例号获取其所有的治疗方案（数组）,优化版
     * */
    @RequestMapping(value = "/file_new/{caseNumber}",method = RequestMethod.GET)
    public Result getNewSchemeInfoByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
        return new Result();
    }

}
