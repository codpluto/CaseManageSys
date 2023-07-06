package com.zhu.casemanage.controller;

import com.aeert.jfilter.annotation.MoreSerializeField;
import com.aeert.jfilter.annotation.SerializeField;
import com.zhu.casemanage.pojo.CasePojo;
import com.zhu.casemanage.pojo.SchemePojo;
import com.zhu.casemanage.pojo.UserPojo;
import com.zhu.casemanage.service.CaseServiceImpl;
import com.zhu.casemanage.service.SchemeServiceImpl;
import com.zhu.casemanage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/platform")
public class ToothController {

    @Autowired
    private CaseServiceImpl caseService;
    @Autowired
    private SchemeServiceImpl schemeService;

    /*
     * 根据病例号获取患者的排牙设定
     * */
    @RequestMapping(value = "/toothSet/caseId/{caseNumber}",method = RequestMethod.GET)
    @MoreSerializeField({
            @SerializeField(clazz = SchemePojo.class, excludes = {"schemeId","toothExtraction","accessoryTeeth","unmovableTeeth",
                    "intervalTeeth","accessoryTeeth","adjacentGlaze","isExcessive","demand"}),
    })
    public Result getToothSetByCaseNumber(@PathVariable("caseNumber") Long caseNumber) {
        SchemePojo scheme = schemeService.getSchemeByNumber(caseNumber);
        return Result.success(scheme);
    }

    /*
     * 根据病例号提交患者的排牙设定
     * */
    @RequestMapping(value = "/toothSet/caseId/addToothSet",method = RequestMethod.POST)
    public Result commitToothSetByCaseNumber(@RequestBody SchemePojo toothSet) {
        schemeService.updateToothSet(toothSet);
        return Result.success();
    }

    /*
     * 根据病例号获取患者的牙位标记
     * */
    @RequestMapping(value = "/toothTag/caseId/{caseNumber}",method = RequestMethod.GET)
    public Result getToothTagByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
        return new Result();
    }

    /*
     * 根据病例号提交患者的牙位标记
     * */
    @RequestMapping(value = "/toothTag/caseId/{caseNumber}",method = RequestMethod.POST)
    public Result commitToothTagByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
        return new Result();
    }

}
