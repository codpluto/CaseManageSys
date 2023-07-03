package com.zhu.casemanage.controller;

import com.zhu.casemanage.utils.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/platform/caseExpress")
public class CaseExpressController {

    /*
     * 根据病例号获取矫治器加工信息
     * */
    @RequestMapping(value = "/getCaseSendInfo/{caseNumber}",method = RequestMethod.GET)
    public Result getCaeSendInfoByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
        return new Result();
    }

    /*
     * 根据病例号提交快递信息
     * */
    @RequestMapping(value = "/caseId/{caseNumber}",method = RequestMethod.POST)
    public Result commitCaseExpressByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
        return new Result();
    }


    /*
     * 根据病例号获取患者的快递信息
     * */
    @RequestMapping(value = "/caseId/{caseNumber}",method = RequestMethod.GET)
    public Result getCaseExpressByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
        return new Result();
    }

    /*
     * 医生对对应病例号的患者进行确认收货操作
     * */
    @RequestMapping(value = "/affirm/{caseNumber}",method = RequestMethod.PUT)
    public Result affirmByDoctor(@PathVariable("caseNumber") String caseNumber) {
        return new Result();
    }

    /*
     * 医生对对应病例号的患者进行加工后续操作
     * 再生产十套也是这个请求
     * */
    @RequestMapping(value = "/docShipments/{caseNumber}",method = RequestMethod.POST)
    public Result docShipmentsByDoctor(@PathVariable("caseNumber") String caseNumber) {
        return new Result();
    }

    /*
     * 主管对对应病例号的患者进行生产操作
     * */
    @RequestMapping(value = "/tecShipments/{caseNumber}",method = RequestMethod.POST)
    public Result tecShipmentsBySupervisor(@PathVariable("caseNumber") String caseNumber) {
        return new Result();
    }

    /*
     * 主管对对应病例号的患者进行发货操作
     * */
    @RequestMapping(value = "/shipments/{caseNumber}",method = RequestMethod.POST)
    public Result shipmentsBySupervisor(@PathVariable("caseNumber") String caseNumber) {
        return new Result();
    }

}
