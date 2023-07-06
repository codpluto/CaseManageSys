package com.zhu.casemanage.controller;

import com.aeert.jfilter.annotation.MoreSerializeField;
import com.aeert.jfilter.annotation.SerializeField;
import com.zhu.casemanage.pojo.CasePojo;
import com.zhu.casemanage.pojo.SchemePojo;
import com.zhu.casemanage.pojo.TrackPojo;
import com.zhu.casemanage.service.CaseServiceImpl;
import com.zhu.casemanage.service.SendServiceImpl;
import com.zhu.casemanage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/platform/caseExpress")
public class CaseExpressController {

    @Autowired
    private SendServiceImpl sendService;

    @Autowired
    private CaseServiceImpl caseService;

    /*
     * 根据病例号获取矫治器加工信息
     * */
    @RequestMapping(value = "/getCaseSendInfo/{caseNumber}",method = RequestMethod.GET)
    public Result getCaseSendInfoByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
        return new Result();
    }

    /*
     * 根据病例号提交快递信息
     * */
    @RequestMapping(value = "/caseId/{caseNumber}",method = RequestMethod.POST)
    public Result updateCaseExpressByCaseNumber(@PathVariable("caseNumber") long caseNumber, @RequestParam int expressId,@RequestParam String expressNum) {
        sendService.updateCaseExpressByCaseNumber(caseNumber,expressId,expressNum);
        return Result.success();
    }


    /*
     * 根据病例号获取患者的快递信息
     * */
    @RequestMapping(value = "/caseId/{caseNumber}",method = RequestMethod.GET)
    @MoreSerializeField({
            @SerializeField(clazz = CasePojo.class, includes = {"caseNumber","expressId","expressNum"}),
    })
    public Result getCaseExpressByCaseNumber(@PathVariable("caseNumber") Long caseNumber) {
        CasePojo caseInfo = caseService.getCaseByNumber(caseNumber);
        return Result.success(caseInfo);
    }

    /*
     * 医生对对应病例号的患者进行确认收货操作
     * */
    @RequestMapping(value = "/affirm/{caseNumber}",method = RequestMethod.PUT)
    public Result affirmByDoctor(@PathVariable("caseNumber") Long caseNumber) {
        CasePojo caseInfo = caseService.getCaseByNumber(caseNumber);
        caseInfo.setCaseState(0);//病例状态待定义，此处修改为“第i此发货完成，等待下一次发货”状态
        /*
        * 实现病例状态记录功能（TrackPojo）
        * */
        return Result.success();
    }

    /*
     * 医生对对应病例号的患者进行加工后续操作
     * 再生产十套也是这个请求
     * */
    @RequestMapping(value = "/docShipments/{caseNumber}",method = RequestMethod.POST)
    public Result docShipmentsByDoctor(@PathVariable("caseNumber") Long caseNumber) {
        CasePojo caseInfo = caseService.getCaseByNumber(caseNumber);
        caseInfo.setCaseState(0);//病例状态待定义，此处修改为“准备生产”状态
        /*
         * 实现病例状态记录功能（TrackPojo）
         * */
        return Result.success();
    }

    /*
     * 主管对对应病例号的患者进行生产操作
     * */
    @RequestMapping(value = "/tecShipments/{caseNumber}",method = RequestMethod.POST)
    public Result tecShipmentsBySupervisor(@PathVariable("caseNumber") Long caseNumber) {
        CasePojo caseInfo = caseService.getCaseByNumber(caseNumber);
        caseInfo.setCaseState(0);//病例状态待定义，此处修改为“生产中”状态
        /*
         * 实现病例状态记录功能（TrackPojo）
         * */
        /*
        * 更新caseInfo表的一些数据
        * */
        return Result.success();
    }

    /*
     * 主管对对应病例号的患者进行发货操作
     * */
    @RequestMapping(value = "/shipments/{caseNumber}",method = RequestMethod.POST)
    public Result shipmentsBySupervisor(@PathVariable("caseNumber") Long caseNumber) {
        CasePojo caseInfo = caseService.getCaseByNumber(caseNumber);
        caseInfo.setCaseState(0);//病例状态待定义，此处修改为“发货中”状态
        /*
         * 实现病例状态记录功能（TrackPojo）
         * */
        /*
         * send表新增
         * */
        return Result.success();
    }

}
