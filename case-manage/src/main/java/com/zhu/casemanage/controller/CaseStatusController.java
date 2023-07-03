package com.zhu.casemanage.controller;

import com.zhu.casemanage.utils.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/platform/caseStatus")
public class CaseStatusController {

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
    public Result name(@PathVariable("caseNumber") String caseNumber) {
        return new Result();
    }

    /*
     * 根据病例号获取该患者的受限情况（是否可以修改病例）
     * */
    @RequestMapping(value = "/limit/{caseNumber}",method = RequestMethod.GET)
    public Result getLimitByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
        return new Result();
    }

    /*
     * 接收或者驳回病例
     * */
    @RequestMapping(value = "/reception",method = RequestMethod.PUT)
    public Result caseReception() {
        return new Result();
    }

    /*
     * 由当前医生自己领取相应病例号的病例（原系统用的align，改为allocate）
     * */
    @RequestMapping(value = "/allocate/{caseNumber}",method = RequestMethod.PUT)
    public Result allocateCase(@PathVariable("caseNumber") String caseNumber) {
        return new Result();
    }

    /*
     * 把相应病例号的病例分配病例给相应的技工
     * */
    @RequestMapping(value = "/allocate/{caseNumber}/{mechanicId}",method = RequestMethod.PUT)
    public Result allocateCaseToMechanic(@PathVariable("caseNumber") String caseNumber,@PathVariable("mechanicId") int mechanicId) {
        return new Result();
    }

//    /*
//     * 排牙完成，由医生将状态改为方案设计中
//     * */
//    @RequestMapping(value = "/toothSet/{caseNumber}/{docotorId}",method = RequestMethod.PUT)
//    public Result toothSetBySupervisor(@PathVariable("caseNumber") String caseNumber,@PathVariable("docotorId") int mechanicId) {
//        return new Result();
//    }
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
    public Result auditByDoctor() {
        return new Result();
    }

    /*
     * 专家评审治疗方案
     * */
    @RequestMapping(value = "/audit/specialist",method = RequestMethod.PUT)
    public Result auditBySpecialist() {
        return new Result();
    }

}
