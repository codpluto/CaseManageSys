package com.zhu.casemanage.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zhu.casemanage.dao.CaseDao;
import com.zhu.casemanage.pojo.CasePojo;
import com.zhu.casemanage.pojo.TDSchemePojo;
import com.zhu.casemanage.pojo.TrackPojo;
import com.zhu.casemanage.service.CaseServiceImpl;
import com.zhu.casemanage.service.SchemeServiceImpl;
import com.zhu.casemanage.service.TrackServiceImpl;
import com.zhu.casemanage.service.UserServiceImpl;
import com.zhu.casemanage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/platform/caseStatus")
public class CaseStatusController {

    @Autowired
    private TrackServiceImpl trackService;
    @Autowired
    private CaseServiceImpl caseService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private SchemeServiceImpl schemeService;
    @Autowired
    private CaseDao caseDao;

    /*
     * 根据病例号获取该患者的所有病例状态（变化记录，数组）
     * */
    @RequestMapping(value = "/tracking/{caseNumber}",method = RequestMethod.GET)
    public Result getTrackingByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
        return new Result();
    }

    /*
     * 根据病例号提交该病例，随后进入（待接收病例）状态
     * */
    @RequestMapping(value = "/temporary/{caseNumber}",method = RequestMethod.PUT)
    public Result name(@PathVariable("caseNumber") Long caseNumber) {
        caseService.updateCaseState(caseNumber,3);
        TrackPojo newTrack = new TrackPojo();
        newTrack.setCaseNumber(caseNumber);
        newTrack.setStatus(104);
        trackService.addTrack(newTrack);
        caseDao.update(null,new LambdaUpdateWrapper<CasePojo>().eq(CasePojo::getCaseNumber,caseNumber).set(CasePojo::getCommitTime,newTrack.getCreateTime()));
        return Result.success();
    }

    /*
     * 根据病例号获取该患者的受限情况（是否可以修改病例）
     * */
    @RequestMapping(value = "/limit/{caseNumber}",method = RequestMethod.GET)
    public Result getLimitByCaseNumber(@PathVariable("caseNumber") Long caseNumber) {
        Boolean limitByNumber = caseService.getLimitByNumber(caseNumber);
        return Result.success(limitByNumber);
    }

    /*
     * 接收或者驳回病例
     * */
    @RequestMapping(value = "/reception",method = RequestMethod.PUT)
    public Result caseReception(@RequestParam("caseNumber") Long caseNumber,
                                @RequestParam("isPass") Boolean isPass,
                                @RequestParam(value = "reason",defaultValue = "") String reason) {
        if (isPass){
            caseService.updateCaseState(caseNumber,4);
            TrackPojo newTrack = new TrackPojo();
            newTrack.setCaseNumber(caseNumber);
            newTrack.setStatus(106);
            trackService.addTrack(newTrack);
        } else {
            caseService.updateCaseState(caseNumber,2);
            TrackPojo newTrack = new TrackPojo();
            newTrack.setCaseNumber(caseNumber);
            newTrack.setStatus(105);
            newTrack.setRemark("理由："+reason);
            trackService.addTrack(newTrack);
        }
        return Result.success();
    }


//    /*
//     * 由当前医生自己领取相应病例号的病例（原系统用的align，改为allocate）
//     * */
//    @RequestMapping(value = "/allocate/{caseNumber}",method = RequestMethod.PUT)
//    public Result allocateCase(@PathVariable("caseNumber") Long caseNumber) {
//
//
//
//        return new Result();
//    }

    /*
     * 把相应病例号的病例分配病例给相应的技工
     * */
    @RequestMapping(value = "/allocate/{caseNumber}/{mechanicId}",method = RequestMethod.PUT)
    public Result allocateCaseToMechanic(@PathVariable("caseNumber") Long caseNumber,@PathVariable("mechanicId") int mechanicId) {
        caseService.updateMechanic(caseNumber,mechanicId);
        caseService.updateCaseState(caseNumber,5);
        TrackPojo newTrack = new TrackPojo();
        newTrack.setCaseNumber(caseNumber);
        newTrack.setStatus(107);
        newTrack.setRemark("分配技工："+userService.getUserInfoById(mechanicId).getUserName());
        trackService.addTrack(newTrack);
        return Result.success();
    }

    /*
     * 排牙完成，由技工将状态改为方案设计中
     * */
    @RequestMapping(value = "/toothSet/{caseNumber}",method = RequestMethod.PUT)
    public Result toothSetBySupervisor(@PathVariable("caseNumber") Long caseNumber) {
        caseService.updateCaseState(caseNumber,6);
        TrackPojo newTrack = new TrackPojo();
        newTrack.setCaseNumber(caseNumber);
        newTrack.setStatus(108);
        trackService.addTrack(newTrack);
        return Result.success();
    }
//
//    /*
//     * 排牙完成，由技工将状态改为方案设计中
//     * */
//    @RequestMapping(value = "/toothSet/{caseNumber}/{mechanicId}",method = RequestMethod.PUT)
//    public Result toothSetByMechanic(@PathVariable("caseNumber") String caseNumber,@PathVariable("mechanicId") int mechanicId) {
//        return new Result();
//    }

    /*
     * 医生评审治疗方案
     * */
    @RequestMapping(value = "/audit/doctor",method = RequestMethod.PUT)
    public Result auditByDoctor(@RequestBody TDSchemePojo tdSchemePojo) {
        TrackPojo newTrack = new TrackPojo();
        newTrack.setCaseNumber(tdSchemePojo.getCaseNumber());
        if (tdSchemePojo.getIsPass()){
            caseService.updateCaseState(tdSchemePojo.getCaseNumber(), 9);
            newTrack.setStatus(110);
        } else {
            caseService.updateCaseState(tdSchemePojo.getCaseNumber(), 6);
            newTrack.setStatus(111);
        }
        trackService.addTrack(newTrack);
        schemeService.update3dScheme(tdSchemePojo);
        return Result.success();
    }

    /*
     * 专家评审治疗方案
     * */
    @RequestMapping(value = "/audit/specialist",method = RequestMethod.PUT)
    public Result auditBySpecialist(@RequestBody TDSchemePojo tdSchemePojo) {
        TrackPojo newTrack = new TrackPojo();
        newTrack.setCaseNumber(tdSchemePojo.getCaseNumber());
        if (tdSchemePojo.getIsPass()){
            caseService.updateCaseState(tdSchemePojo.getCaseNumber(), 9);
            newTrack.setStatus(112);
        } else {
            caseService.updateCaseState(tdSchemePojo.getCaseNumber(), 6);
            newTrack.setStatus(113);
        }
        trackService.addTrack(newTrack);
        schemeService.update3dScheme(tdSchemePojo);
        return Result.success();
    }

    /*
    * 上传3d方案
    * */
    @RequestMapping(value = "/3dScheme",method = RequestMethod.POST)
    public Result add3dScheme(@RequestBody TDSchemePojo tdSchemePojo){
        schemeService.add3dScheme(tdSchemePojo);
        TrackPojo newTrack = new TrackPojo();
        newTrack.setCaseNumber(tdSchemePojo.getCaseNumber());
        newTrack.setStatus(109);
        trackService.addTrack(newTrack);
        switch (tdSchemePojo.getAuditorType()){
            case 1 : caseService.updateCaseState(tdSchemePojo.getCaseNumber(), 7);break;
            case 2 : caseService.updateCaseState(tdSchemePojo.getCaseNumber(), 8);break;
        }
        return Result.success();
    }



}
