package com.zhu.casemanage.controller;

import com.aeert.jfilter.annotation.MoreSerializeField;
import com.aeert.jfilter.annotation.SerializeField;
import com.alibaba.fastjson2.JSON;
import com.zhu.casemanage.pojo.CasePojo;
import com.zhu.casemanage.pojo.FilePojo;
import com.zhu.casemanage.pojo.SchemePojo;
import com.zhu.casemanage.service.CaseServiceImpl;
import com.zhu.casemanage.service.FileServiceImpl;
import com.zhu.casemanage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/platform/cmCaseInfo")
public class CaseInfoController {
    @Autowired
    private CaseServiceImpl caseService;
    @Autowired
    private FileServiceImpl fileService;

    /*
     * 获取指定病例号的病例信息
     * */
    @RequestMapping(value = "/{caseNumber}",method = RequestMethod.GET)
    public Result getCaseInfoByCaseNumber(@PathVariable("caseNumber") long caseNumber) {
        return new Result();
    }

    /*
     * 获取指定病例号的3D病例信息
     * */
    @RequestMapping(value = "3dcmCaseInfo/{caseNumber}",method = RequestMethod.GET)
    public Result get3DCaseInfoByCaseNumber(@PathVariable("caseNumber") long caseNumber) {
        return new Result();
    }

    /*
     * 获取指定病例号的pdf病例信息
     * */
    @RequestMapping(value = "pdfCaseInfo/{caseNumber}",method = RequestMethod.GET)
    public Result getPDFCaseInfoByCaseNumber(@PathVariable("caseNumber") long caseNumber) {
        return new Result();
    }

    /*
     * 删除指定病例号的病例信息
     * */
    @RequestMapping(value = "/{caseNumber}",method = RequestMethod.DELETE)
    public Result deleteCaseInfoByCaseNumber(@PathVariable("caseNumber") long caseNumber) {
        caseService.delCase(caseNumber);
        return Result.success();
    }

    //
//    @RequestMapping(value = "/{caseNumber}",method = RequestMethod.DELETE)
//    public Result deleteCaseInfoByCaseNumber(@PathVariable("caseNumber") long caseNumber) {
//        caseService.delKeepCase(caseNumber);
//        return Result.success();
//    }


    /*
     * 根据病例号获取暂存的患者信息
     * */
    @RequestMapping(value = "/record/{caseNumber}",method = RequestMethod.GET)
//    @MoreSerializeField({
//            @SerializeField(clazz = CasePojo.class, includes = {"birthday","patientName","gender","addressId",
//                    "doctorId"}),
//    })
    public Result getRecordCaseInfoByCaseNumber(@PathVariable("caseNumber") long caseNumber) {
        CasePojo caseByNumber = caseService.getCaseByNumber(caseNumber);
        List<FilePojo> imageList = fileService.getImageListByNumber(caseNumber);
//        HashMap<String, Object> map = JSON.parseObject(JSON.toJSONString(caseByNumber));
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

//    @RequestMapping(value = "/record/{caseId}",method = RequestMethod.GET)
//    public Result getRecordCaseInfoByCaseNumber(@PathVariable("caseId") int caseId) {
//        CasePojo caseById = caseService.findCaseById(caseId);
//        return Result.success(caseById);
//    }


    /*
     * 暂存患者信息
     * */
    @RequestMapping(value = "/record",method = RequestMethod.POST)
    public Result recordCaseInfo(@RequestBody CasePojo newCase) {
        caseService.addCase(newCase);
        return Result.success();
    }

    //修改病例信息
    @RequestMapping(value = "/record",method = RequestMethod.PUT)
    public Result updateCaseInfo(@RequestBody CasePojo casePojo) {
        caseService.updateCase(casePojo);
        return Result.success();
    }


    /*
     * 返回全部病例的第pageNum页（默认状态为全部）
     * */
    @RequestMapping(value = "/cmCaseInfo/{pageNum}/{pageSize}",method = RequestMethod.GET)
    public Result showCaseInfo(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        return new Result();
    }

    /*
     * 返回按所选状态(selectStatus)的全部病例的第pageNum页
     * */
//    @RequestMapping(value = "/cmCaseInfo/dispose/{pageNum}/{pageSize}",method = RequestMethod.GET)
//    public Result showCaseInfoBySelectStatus(@PathVariable("pageNum") int pageNum,@PathVariable("selectStatus") int selectStatus ,@PathVariable("pageSize") int pageSize) {
//        return new Result();
//    }

    /*
     * 返回按关键字搜索(selectParam)的全部病例的第pageNum页
     * */
//    @RequestMapping(value = "/cmCaseInfo/dispose/{pageNum}/{pageSize}",method = RequestMethod.GET)
//    public Result showCaseInfoBySelectParam(@PathVariable("pageNum") int pageNum,@PathVariable("selectParam") int selectStatus ,@PathVariable("pageSize") int pageSize) {
//        return new Result();
//    }

    /*
     * 返回待处理病例的第pageNum页（默认状态为全部）
     * */
//    @RequestMapping(value = "/cmCaseInfo/dispose/{pageNum}/{pageSize}",method = RequestMethod.GET)
//    public Result showDisposeCaseInfo(@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize) {
//        return new Result();
//    }

    /*
     * 返回按所选状态的待处理病例的第pageNum页
     * */
//    @RequestMapping(value = "/cmCaseInfo/dispose/{pageNum}/{pageSize}",method = RequestMethod.GET)
//    public Result showDisposeCaseInfoBySelectStatus(@PathVariable("pageNum") int pageNum,@PathVariable("selectStatus") int selectStatus,@PathVariable("pageSize") int pageSize) {
//        return new Result();
//    }

    /*
     * 根据病例号获取患者的临床信息
     * */
    @RequestMapping(value = "/clinicalCircumstance/{caseNumber}",method = RequestMethod.GET)
    @MoreSerializeField({
            @SerializeField(clazz = CasePojo.class, includes = {"diagnosisInfos","caseNumber","doctorRemark"}),
    })
    public Result getClinicalCircumstanceByCaseNumber(@PathVariable("caseNumber") Long caseNumber) {
        CasePojo clinicalCircumstance = caseService.getCaseByNumber(caseNumber);
        return Result.success(clinicalCircumstance);
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

    /*
     * 重启病例(阶段调整)，修改旧的病例号为新的病例号
     * */
    @RequestMapping(value = "/restart/{oldCaseNumber}/{newCaseNumber}",method = RequestMethod.POST)
    public Result restartCase(@PathVariable("oldCaseNumber") long oldCaseNumber,@PathVariable("newCaseNumber") long newCaseNumber) {
        return new Result();
    }




}
