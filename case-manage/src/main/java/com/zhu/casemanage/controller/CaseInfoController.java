package com.zhu.casemanage.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhu.casemanage.pojo.*;
import com.zhu.casemanage.service.*;
import com.zhu.casemanage.utils.Constant;
import com.zhu.casemanage.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping(value = "/platform/cmCaseInfo")
public class CaseInfoController {
    @Autowired
    private CaseServiceImpl caseService;
    @Autowired
    private FileServiceImpl fileService;
    @Autowired
    private TrackServiceImpl trackService;
    @Autowired
    private SchemeServiceImpl schemeService;
    @Autowired
    private SendServiceImpl sendService;
    @Autowired
    private ObjectMapper objectMapper;


    /*
     * 获取指定病例号的病例信息
     * */
    @RequestMapping(value = "/{caseNumber}",method = RequestMethod.GET)
    public Result getCaseInfoByCaseNumber(@PathVariable("caseNumber") long caseNumber) {
        CasePojo caseInfo = caseService.getCaseByNumber(caseNumber);
        return Result.success(caseInfo);
    }

    /*
     * 删除指定病例号的病例信息
     * */
    @RequestMapping(value = "/{caseNumber}",method = RequestMethod.DELETE)
    public Result deleteCaseInfoByCaseNumber(@PathVariable("caseNumber") long caseNumber) {
        caseService.delCase(caseNumber);
        return Result.success();
    }

//    @RequestMapping(value = "/{caseNumber}",method = RequestMethod.DELETE)
//    public Result deleteCaseInfoByCaseNumber(@PathVariable("caseNumber") long caseNumber) {
//        caseService.delKeepCase(caseNumber);
//        return Result.success();
//    }


    /*
     * 根据病例号获取暂存的患者信息
     * */
    @RequestMapping(value = "/record/{caseNumber}",method = RequestMethod.GET)
    public Result getRecordCaseInfoByCaseNumber(@PathVariable("caseNumber") Long caseNumber) {
        CasePojo caseByNumber = caseService.getCaseByNumber(caseNumber);
        List<FilePojo> imageList = fileService.getImageListByNumber(caseNumber);
        HashMap<String, Object> map = new HashMap<>();
        map.put("caseNumber",caseByNumber.getCaseNumber());
        map.put("patientName",caseByNumber.getPatientName());
        map.put("gender",caseByNumber.getGender());
        map.put("birthday",caseByNumber.getBirthday());
        map.put("doctorId",caseByNumber.getDoctorId());
        map.put("addressId",caseByNumber.getAddressId());
        map.put("patientComplaint",caseByNumber.getPatientComplaint());
        map.put("imageList",imageList);
        return Result.success(map);
    }



    /*
     * 新增患者信息
     * */
    @RequestMapping(value = "/record",method = RequestMethod.POST)
//    public Result recordCaseInfo(@RequestBody CasePojo newCase) {
    public Result recordCaseInfo(@RequestBody Map<String,Object> newCase) throws JsonProcessingException {
        //map转对象
        String json = objectMapper.writeValueAsString(newCase.get("userInfo"));
        CasePojo newCaseInfo = objectMapper.readValue(json, CasePojo.class);
        //新增病例
        newCaseInfo.setCaseState(1);
        caseService.addCase(newCaseInfo);
        TrackPojo newTrack = new TrackPojo();
        newTrack.setCaseNumber(newCaseInfo.getCaseNumber());
        newTrack.setStatus(101);
        trackService.addTrack(newTrack);
        HashMap<String, Object> map = new HashMap<>();
        map.put("caseNumber",newCaseInfo.getCaseNumber().toString());
        //新增图片
        if (newCase.get("imageList") != null){
            String jsonImage = objectMapper.writeValueAsString(newCase.get("imageList"));
            List<FilePojo> imageList = objectMapper.readValue(jsonImage, objectMapper.getTypeFactory().constructCollectionType(List.class, FilePojo.class));

            for (FilePojo image:
                    imageList) {
                image.setCaseNumber(newCaseInfo.getCaseNumber());
                fileService.addFile(image);
            }
//            map.put("containsImage",true);
        }
        return Result.success(map);
    }

    //修改患者信息
    @RequestMapping(value = "/record",method = RequestMethod.PUT)
    public Result updateCaseInfo(@RequestBody CasePojo casePojo) {
        caseService.updateCase(casePojo);
        return Result.success();
    }


    /*
     * 返回全部病例的第pageNum页（默认状态为全部）
     * */
    @RequestMapping(value = "/cmCaseInfo/{pageNum}/{pageSize}",method = RequestMethod.GET)
    public Result showCaseInfo(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize,
                               @RequestParam(value = "selectStatus", defaultValue = "-1") int selectStatus,
                               @RequestParam(value = "selectParam",defaultValue = "") String selectParam) {
        Map<String, Object> map = caseService.showCaseInfoPage(pageNum, pageSize,selectStatus,selectParam);
        return Result.success(map);
    }



    /*
     * 返回待处理病例筛选后的第pageNum页
     * */
    @RequestMapping(value = "/cmCaseInfo/dispose/{pageNum}/{pageSize}",method = RequestMethod.GET)
    public Result showDisposeCaseInfo(@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize,
                                      @RequestParam(value = "selectStatus", defaultValue = "-1") int selectStatus,
                                      @RequestParam(value = "selectParam",defaultValue = "") String selectParam,
                                      @RequestParam("userType") int userType) {
        Map<String, Object> map = caseService.showCaseInfoPageByUserType(pageNum, pageSize,userType, selectStatus, selectParam);

        return Result.success(map);
    }



    /*
     * 根据病例号获取患者的临床信息
     * */
    @RequestMapping(value = "/clinicalCircumstance/{caseNumber}",method = RequestMethod.GET)
    public Result getClinicalCircumstanceByCaseNumber(@PathVariable("caseNumber") Long caseNumber) {
        CasePojo clinicalCircumstance = caseService.getCaseByNumber(caseNumber);
        HashMap<String, Object> map = new HashMap<>();
        map.put("diagnosisInfos",clinicalCircumstance.getDiagnosisInfos());
        map.put("doctorRemark",clinicalCircumstance.getDoctorRemark());
        return Result.success(map);
    }

    /*
     * 根据病例号提交患者的临床信息
     * */
    @RequestMapping(value = "/clinicalCircumstance/{caseNumber}",method = RequestMethod.PUT)
    public Result commitClinicalCircumstanceByCaseNumber(@PathVariable("caseNumber") Long caseNumber,
                                                         @RequestParam String diagnosisInfos,
                                                         @RequestParam String doctorRemark) {
        caseService.updateClinicalCircumstance(caseNumber,diagnosisInfos,doctorRemark);
        return Result.success();
    }

    @RequestMapping(value = "/clinicalCircumstance/{caseNumber}",method = RequestMethod.POST)
    public Result postClinicalCircumstanceByCaseNumber(@PathVariable("caseNumber") Long caseNumber,
                                                         @RequestParam String diagnosisInfos,
                                                         @RequestParam String doctorRemark) {
        caseService.updateClinicalCircumstance(caseNumber,diagnosisInfos,doctorRemark);
        return Result.success();
    }

    /*
     * 重启病例(阶段调整)，修改旧的病例号为新的病例号
     * */
    @RequestMapping(value = "/restart/{oldCaseNumber}",method = RequestMethod.POST)
    public Result restartCase(@PathVariable("oldCaseNumber") Long oldCaseNumber) {
        caseService.updateCaseState(oldCaseNumber,13);
        TrackPojo newTrack = new TrackPojo();
        newTrack.setCaseNumber(oldCaseNumber);
        newTrack.setStatus(119);

        CasePojo newCase = caseService.restartCase(oldCaseNumber);

        newTrack.setRemark("新病例号："+newCase.getCaseNumber().toString());
        trackService.addTrack(newTrack);

        SchemePojo oldCaseScheme = schemeService.getSchemeByNumber(oldCaseNumber);
        if (oldCaseScheme != null){
            oldCaseScheme.setCaseNumber(newCase.getCaseNumber());
            oldCaseScheme.setSchemeId(null);
            schemeService.addScheme(oldCaseScheme);
        }

        List<FilePojo> oldCaseFiles = fileService.getFileListByCaseNumber(oldCaseNumber);
        if (oldCaseFiles.size() > 0){
            for (FilePojo file:
                    oldCaseFiles) {
                file.setCaseNumber(newCase.getCaseNumber());
                file.setFileId(null);
                fileService.addFile(file);
                if (file.getFileType() == 1){
                    caseService.updateCaseFacePhoto(file.getCaseNumber(), file.getFileUrl());
                }
                if (file.getFileType() >= 14 && file.getFileType() <= 16){
                    TrackPojo track = trackService.getTrackByStatus(file.getCaseNumber(), 103);
                    if (track == null){
                        TrackPojo newTrack1 = new TrackPojo();
                        newTrack1.setCaseNumber(file.getCaseNumber());
                        newTrack1.setStatus(103);
                        trackService.addTrack(newTrack1);
                        if (caseService.getCaseByNumber(file.getCaseNumber()).getCaseState() < 2){
                            caseService.updateCaseState(file.getCaseNumber(), 2);
                        }
                    }
                }
            }
        }

        SendPojo oldCaseExpress1 = sendService.getCaseExpressByCaseNumber(oldCaseNumber);
        if (oldCaseExpress1 != null){
            oldCaseExpress1.setCaseNumber(newCase.getCaseNumber());
            oldCaseExpress1.setSendId(null);
            sendService.addCaseExpress(oldCaseExpress1);
            TrackPojo newTrack1 = new TrackPojo();
            newTrack1.setCaseNumber(oldCaseExpress1.getCaseNumber());
            newTrack1.setStatus(102);
            newTrack1.setRemark(Constant.EXPRESS.get(oldCaseExpress1.getExpressId())+" "+oldCaseExpress1.getExpressNum());
            trackService.addTrack(newTrack1);
            if (caseService.getCaseByNumber(newTrack1.getCaseNumber()).getCaseState() < 2){
                caseService.updateCaseState(oldCaseExpress1.getCaseNumber(), 2);
            }
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("newCaseNumber",newCase.getCaseNumber());
        return Result.success(map);
    }



}
