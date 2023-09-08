package com.zhu.casemanage.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhu.casemanage.constant.UserConstant;
import com.zhu.casemanage.dao.SendDao;
import com.zhu.casemanage.pojo.CasePojo;
import com.zhu.casemanage.pojo.SendPojo;
import com.zhu.casemanage.pojo.TrackPojo;
import com.zhu.casemanage.service.CaseServiceImpl;
import com.zhu.casemanage.service.SendServiceImpl;
import com.zhu.casemanage.service.TrackServiceImpl;
import com.zhu.casemanage.utils.Constant;
import com.zhu.casemanage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/platform/caseExpress")
public class CaseExpressController {

    @Autowired
    private SendServiceImpl sendService;

    @Autowired
    private CaseServiceImpl caseService;

    @Autowired
    private TrackServiceImpl trackService;
    @Autowired
    private SendDao sendDao;

    /*
     * 根据病例号获取矫治器加工信息
     * */
    @RequestMapping(value = "/getCaseSendInfo/{caseNumber}",method = RequestMethod.GET)
    public Result getCaseSendInfoByCaseNumber(@PathVariable("caseNumber") Long caseNumber) {
//        List<SendPojo> sendList = sendService.getSendListByCaseNumber(caseNumber);
        List<SendPojo> lastSendList = sendService.CaseExpressDesc(caseNumber);
        CasePojo casePojo = caseService.getCaseByNumber(caseNumber);
        int wearRemain = casePojo.getWearRemain();
        int lowerSentStep = casePojo.getLowerSentStep();
        int lowerTotalStep = casePojo.getLowerTotalStep();
        int upperSentStep = casePojo.getUpperSentStep();
        int upperTotalStep = casePojo.getUpperTotalStep();
        int wearStep = casePojo.getWearStep();
//        HashMap<String, Object> sendListNew = new HashMap<>();
//        sendListNew.put("item", lastSendList.get(0));
        HashMap<String, Object> result = new HashMap<>();
        result.put("sendList",lastSendList);

        result.put("wearRemain",wearRemain);
        result.put("lowerSentStep",lowerSentStep);
        result.put("lowerTotalStep",lowerTotalStep);
        result.put("upperSentStep",upperSentStep);
        result.put("upperTotalStep",upperTotalStep);
        result.put("wearStep",wearStep);
        return Result.success(result);
    }

    /*
     * 根据病例号提交快递信息
     * */
//    @RequestMapping(value = "/caseId/{caseNumber}",method = RequestMethod.POST)
//    public Result addCaseExpress(@PathVariable("caseNumber") Long caseNumber, @RequestParam int expressId,@RequestParam String expressNum) {
//        sendService.updateCaseExpressByCaseNumber(caseNumber,expressId,expressNum);
//        return Result.success();
//    }

    /*
    * 提交牙模寄出的快递信息
    * */
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Result addCaseExpress(@RequestBody SendPojo newExpress){
        SendPojo sendPojo = sendDao.selectOne(new LambdaQueryWrapper<SendPojo>().eq(SendPojo::getCaseNumber, newExpress.getCaseNumber()).eq(SendPojo::getExpressType,1));
        if (sendPojo == null){
            newExpress.setExpressType(1);//1牙模寄出，2发货
            sendService.addCaseExpress(newExpress);
            TrackPojo newTrack = new TrackPojo();
            newTrack.setCaseNumber(newExpress.getCaseNumber());
            newTrack.setStatus(102);
            newTrack.setStatusName(UserConstant.TRACK.STATUS102);
            newTrack.setRemark(Constant.EXPRESS.get(newExpress.getExpressId())+" "+newExpress.getExpressNum());
            trackService.addTrack(newTrack);
            if (caseService.getCaseByNumber(newTrack.getCaseNumber()).getCaseState() < 2){
                caseService.updateCaseState(newExpress.getCaseNumber(), 2);
            }
        } else {
            sendService.updateCaseExpress(newExpress);
            trackService.updateCaseExpress(newExpress);
        }
        return Result.success();
    }



    /*
     * 根据病例号获取患者的快递信息
     * */
    @RequestMapping(value = "/caseNumber/{caseNumber}",method = RequestMethod.GET)
//    @MoreSerializeField({
//            @SerializeField(clazz = SendPojo.class, includes = {"caseNumber","expressId","expressNum"}),
//    })
    public Result getCaseExpressByCaseNumber(@PathVariable("caseNumber") Long caseNumber) {
        SendPojo caseExpress = sendService.getCaseExpressByCaseNumber(caseNumber);
        if (caseExpress == null){
            return Result.success();
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("caseNumber",caseExpress.getCaseNumber());
        map.put("expressId",caseExpress.getExpressId());
        map.put("expressNum",caseExpress.getExpressNum());
        return Result.success(map);
    }

    /*
    * 修改牙模寄出的快递信息
    * */
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public Result updateCaseExpress(@RequestBody SendPojo newSend){
        sendService.updateCaseExpress(newSend);
        trackService.updateCaseExpress(newSend);
        return Result.success();
    }


    /*
     * 医生对对应病例号的患者进行确认收货操作
     * */
    @RequestMapping(value = "/affirm/{caseNumber}",method = RequestMethod.PUT)
    public Result affirmByDoctor(@PathVariable("caseNumber") Long caseNumber) {
        SendPojo sendInfo = sendService.CaseExpressDesc(caseNumber).get(0);
        CasePojo caseInfo = caseService.getCaseByNumber(caseNumber);
        if (caseInfo.getLowerSentStep() >= caseInfo.getLowerTotalStep() && caseInfo.getUpperSentStep() >= caseInfo.getUpperTotalStep()) {
            caseService.updateCaseState(caseNumber,14);
            TrackPojo newTrack1 = new TrackPojo();
            newTrack1.setCaseNumber(caseNumber);
            newTrack1.setStatus(117);
            newTrack1.setStatusName(UserConstant.TRACK.STATUS117);
            newTrack1.setRemark("物流单号："+ sendInfo.getExpressNum());
            newTrack1.setRemarkEn("Tracking Num:"+sendInfo.getExpressNum());
            TrackPojo newTrack2 = new TrackPojo();
            newTrack2.setCaseNumber(caseNumber);
            newTrack2.setStatus(120);
            newTrack2.setStatusName(UserConstant.TRACK.STATUS120);
            trackService.addTrack(newTrack1);
            trackService.addTrack(newTrack2);
        }
        else {
            long count = trackService.countStatus(caseNumber,118) + 1;//记录本次是第几次发货
            caseService.updateCaseState(caseNumber,12);//此处修改为“第i此发货完成，等待下一次发货”状态
            TrackPojo newTrack1 = new TrackPojo();
            newTrack1.setCaseNumber(caseNumber);
            newTrack1.setStatus(117);//此处新增“已确认收货”
            newTrack1.setStatusName(UserConstant.TRACK.STATUS117);
            newTrack1.setRemark("物流单号："+ sendInfo.getExpressNum());
            newTrack1.setRemarkEn("Tracking Num:"+sendInfo.getExpressNum());
            TrackPojo newTrack2 = new TrackPojo();
            newTrack2.setCaseNumber(caseNumber);
            newTrack2.setStatus(118);//此处新增为“第i此发货完成，等待下一次发货”
            newTrack2.setStatusName(UserConstant.TRACK.STATUS118);
            newTrack2.setRemark(Long.toString(count));
            newTrack2.setRemarkEn(Long.toString(count));
            trackService.addTrack(newTrack1);
            trackService.addTrack(newTrack2);
        }
        return Result.success();
    }

    /*
     * 医生对对应病例号的患者进行加工后续操作
     * 再生产十套也是这个请求
     * */
    @RequestMapping(value = "/docShipments/{caseNumber}",method = RequestMethod.POST)
    public Result docShipmentsByDoctor(@PathVariable("caseNumber") Long caseNumber) {
        caseService.updateCaseState(caseNumber,9);//此处修改为“准备生产”状态
        return Result.success();
    }

    /*
     * 主管对对应病例号的患者进行生产操作
     * */
    @RequestMapping(value = "/tecShipments/{caseNumber}",method = RequestMethod.POST)
    public Result tecShipmentsBySupervisor(@PathVariable("caseNumber") Long caseNumber,@RequestBody SendPojo newSend) {
        CasePojo caseInfo = caseService.getCaseByNumber(caseNumber);
        caseService.updateCaseState(caseNumber,10);//此处修改为“生产中”状态
        TrackPojo newTrack = new TrackPojo();
        newTrack.setCaseNumber(caseNumber);
        newTrack.setStatus(115);//此处新增"安排生产"
        newTrack.setStatusName(UserConstant.TRACK.STATUS115);
        trackService.addTrack(newTrack);
        if (newSend.getStepsLowOver() > caseInfo.getLowerSentStep()) {
            caseService.updateLowerSentStep(caseNumber,newSend.getStepsLowOver());
        }
        if (newSend.getStepsUpOver() > caseInfo.getUpperSentStep()) {
            caseService.updateUpperSentStep(caseNumber,newSend.getStepsUpOver());
        }
        return Result.success();
    }

    /*
     * 主管对对应病例号的患者进行发货操作
     * */
    @RequestMapping(value = "/shipments/{caseNumber}",method = RequestMethod.POST)
    public Result shipmentsBySupervisor(@PathVariable("caseNumber") Long caseNumber,@RequestBody SendPojo newSend) {
        caseService.updateCaseState(caseNumber,11);//此处修改为“发货中”状态
        TrackPojo newTrack = new TrackPojo();
        newTrack.setCaseNumber(caseNumber);
        newTrack.setStatus(116);//此处新增"矫治器生产完成"
        newTrack.setStatusName(UserConstant.TRACK.STATUS116);
        newTrack.setRemark("U:"+ newSend.getStepsLowStart() + "/" + newSend.getStepsLowOver()
                            + " L:" + newSend.getStepsUpStart() + "/" + newSend.getStepsUpOver());
        newTrack.setRemarkEn("U:"+ newSend.getStepsLowStart() + "/" + newSend.getStepsLowOver()
                + " L:" + newSend.getStepsUpStart() + "/" + newSend.getStepsUpOver());
        trackService.addTrack(newTrack);
        newSend.setExpressType(2);
        newSend.setCaseNumber(caseNumber);
        sendService.addCaseExpress(newSend);
        return Result.success();
    }

}
