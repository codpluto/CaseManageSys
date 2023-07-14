package com.zhu.casemanage.controller;


import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.pojo.CasePojo;
import com.zhu.casemanage.pojo.SchemePojo;
import com.zhu.casemanage.pojo.UserPojo;
import com.zhu.casemanage.service.CaseServiceImpl;
import com.zhu.casemanage.service.SchemeServiceImpl;
import com.zhu.casemanage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
    @RequestMapping(value = "/toothSet/caseNumber/{caseNumber}",method = RequestMethod.GET)
    public Result getToothSetByCaseNumber(@PathVariable("caseNumber") Long caseNumber) {
        SchemePojo scheme = schemeService.getSchemeByNumber(caseNumber);
        HashMap<String, Object> map = new HashMap<>();
        map.put("preinstall",scheme.getPreinstall());
        map.put("orthodonticArch",scheme.getOrthodonticArch());
        map.put("extTooth",scheme.getExtTooth());
        map.put("anchorageLeft",scheme.getAnchorageLeft());
        map.put("anchorageRight",scheme.getAnchorageRight());
        map.put("centerlineChoiceUp",scheme.getCenterlineChoiceUp());
        map.put("centerlineChoiceLow",scheme.getCenterlineChoiceLow());
        map.put("centerlineUpVal",scheme.getCenterlineUpVal());
        map.put("centerlineLowVal",scheme.getCenterlineLowVal());
        map.put("overbiteAdjust",scheme.getOverbiteAdjust());
        map.put("overjetAdjust",scheme.getOverjetAdjust());
        map.put("overjetVal",scheme.getOverjetVal());
        map.put("molarRelationshipLeft",scheme.getMolarRelationshipLeft());
        map.put("molarRelationshipRight",scheme.getMolarRelationshipRight());
        map.put("crowdingToothUp",scheme.getCrowdingToothUp());
        map.put("crowdingToothLow",scheme.getCrowdingToothLow());
        map.put("crowdingToothUpVal",scheme.getCrowdingToothUpVal());
        map.put("crowdingToothLowVal",scheme.getCrowdingToothLowVal());
        map.put("move",scheme.getMove());
        map.put("angle",scheme.getAngle());
        return Result.success(map);
    }

    /*
     * 根据病例号提交患者的排牙设定
     * */
    @RequestMapping(value = "/toothSet",method = RequestMethod.POST)
    public Result commitToothSetByCaseNumber(@RequestBody SchemePojo toothSet) {
        schemeService.addScheme(toothSet);
        return Result.success();
    }

    /*
    * 修改排牙设定
    * */
    @RequestMapping(value = "/toothSet",method = RequestMethod.PUT)
    public Result updateToothSet(@RequestBody SchemePojo toothSet){
        schemeService.updateToothSet(toothSet);
        return Result.success();
    }


    /*
     * 根据病例号获取患者的牙位标记
     * */
    @RequestMapping(value = "/toothTag/caseNumber/{caseNumber}",method = RequestMethod.GET)
    public Result getToothTagByCaseNumber(@PathVariable("caseNumber") Long caseNumber) {
        SchemePojo scheme = schemeService.getSchemeByNumber(caseNumber);
        if (scheme == null){
            throw new BusinessException("病例不存在");
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("unmovableTeeth",scheme.getUnmovableTeeth());
        map.put("accessoryTeeth",scheme.getAccessoryTeeth());
        map.put("intervalTeeth",scheme.getIntervalTeeth());
        map.put("adjacentGlaze",scheme.getAdjacentGlaze());
        map.put("isExcessive",scheme.getIsExcessive());
        map.put("demand",scheme.getDemand());
        return Result.success(map);
    }

    /*
     * 根据病例号提交患者的牙位标记
     * */
    @RequestMapping(value = "/toothTag",method = RequestMethod.POST)
    public Result commitToothTagByCaseNumber(@RequestBody SchemePojo toothTag) {
        schemeService.updateToothTag(toothTag);
        return Result.success();
    }

    /*
    * 修改牙位标记
    * */
    @RequestMapping(value = "/toothTag",method = RequestMethod.PUT)
    public Result updateToothTag(@RequestBody SchemePojo toothTag){
        schemeService.updateToothTag(toothTag);
        return Result.success();
    }

}
