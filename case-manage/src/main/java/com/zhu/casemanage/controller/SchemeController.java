package com.zhu.casemanage.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhu.casemanage.constant.UserConstant;
import com.zhu.casemanage.dao.CaseDao;
import com.zhu.casemanage.dao.FileDao;
import com.zhu.casemanage.dao.TrackDao;
import com.zhu.casemanage.pojo.*;
import com.zhu.casemanage.service.FileServiceImpl;
import com.zhu.casemanage.service.SchemeServiceImpl;
import com.zhu.casemanage.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping(value = "/platform/cmScheme")
public class SchemeController {

    @Autowired
    private SchemeServiceImpl schemeService;
    @Autowired
    private FileServiceImpl fileService;
    @Autowired
    private FileDao fileDao;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CaseDao caseDao;
    @Autowired
    private TrackDao trackDao;

    @Value("${file-save-path}")
    private String fileSavePath;

    /*
     * 根据病例号获取其所有的治疗方案（列表）
     * */
//    @RequestMapping(value = "/file/{caseNumber}",method = RequestMethod.GET)
//    public Result getSchemeInfoByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
//        return new Result();
//    }

//    /*
//     * 根据病例号获取其所有的批复记录（列表）
//     * */
//    @RequestMapping(value = "/audit/list/caseId/{caseNumber}/recNum/{recNum}/seqNum/{seqNum}",method = RequestMethod.GET)
//    public Result getAuditListByCaseNumber(@PathVariable("caseNumber") Long caseNumber,@PathVariable("recNum") String recNum,@PathVariable("seqNum") String seqNum) {
//        return new Result();
//    }
//
//    /*
//     * 判断指定病历号的3D设计方案是否已审核过（true为未审核）
//     * */
//    @RequestMapping(value = "/scheme/judge/{caseNumber}",method = RequestMethod.GET)
//    public Result getAuditListByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
//        return new Result();
//    }
//
//    /*
//     * 根据病例号获取其所有的治疗方案（数组）,优化版
//     * */
//    @RequestMapping(value = "/file_new/{caseNumber}",method = RequestMethod.GET)
//    public Result getNewSchemeInfoByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
//        return new Result();
//    }

    /*
    * 新增治疗方案（新增病例界面）
    * */
    @RequestMapping(value = "/scheme/addScheme",method = RequestMethod.POST)
    public Result addScheme(@RequestBody SchemePojo newScheme){
        schemeService.addScheme(newScheme);
        return Result.success();
    }

    /*
    * 根据病例号查询治疗方案
    * */
    @RequestMapping(value = "/scheme/{caseNumber}",method = RequestMethod.GET)
    public Result getScheme(@PathVariable("caseNumber") Long caseNumber) {
        SchemePojo caseScheme = schemeService.getSchemeByNumber(caseNumber);
        return Result.success(caseScheme);
    }



    /*
    * 根据病例号获取3d方案列表
    * */
    @RequestMapping(value = "/3dScheme/list/{caseNumber}",method = RequestMethod.GET)
    public Result get3dSchemeList(@PathVariable("caseNumber") Long caseNumber){
        List<TDSchemePojo> tdSchemeList = schemeService.get3dSchemeList(caseNumber);
        return Result.success(tdSchemeList);
    }



    /*
    * 新增3D方案
    * */
    @RequestMapping(value = "/3dScheme",method = RequestMethod.POST)
    public Result add3dScheme(@RequestBody TDSchemePojo tdSchemePojo){
        schemeService.add3dScheme(tdSchemePojo);
        return Result.success();
    }


    /*
    * 修改3D方案
    * */
//    @RequestMapping(value = "/3dScheme",method = RequestMethod.PUT)
//    public Result update3dScheme()

    /*
    * 上传设计方案文档
    * */
    @RequestMapping(value = "/schemeFile",method = RequestMethod.POST)
    public Result uploadSchemeFile(@RequestParam("scheme") String scheme,
                                   @RequestParam("file") MultipartFile file,
                                   HttpServletRequest req) throws IOException {
        //将Json序列化为Diary类
        String json = scheme;
        Map<String, Object> map = objectMapper.readValue(json, Map.class);
        String fileUrl = (String) map.get("fileUrl");
        String upperSteps = (String) map.get("upperSteps");
        String lowerSteps = (String) map.get("lowerSteps");
        Long caseNumber = (Long) map.get("caseNumber");


        //获取文件原始名称
        String originalFilename = file.getOriginalFilename();
        //获取文件的类型
        String type = FileUtil.extName(originalFilename);
        log.info("文件类型是：" + type);
        //获取文件大小
        long size = file.getSize();

        //文件存储的磁盘
        File uploadParentFile = new File(fileSavePath);
        //判断文件目录是否存在
        if(!uploadParentFile.exists()) {
            //如果不存在就创建文件夹
            uploadParentFile.mkdirs();
        }
        //定义一个文件唯一标识码（UUID）
        String uuid = UUID.randomUUID().toString();
        String fileUUID = uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileSavePath + fileUUID);
        File dest = new File(uploadFile.getAbsolutePath());
        //将临时文件转存到指定磁盘位置
        file.transferTo(dest);

        //设置下载的文件路径
        String url = req.getScheme() + "://" + req.getServerName() + ":" +
                req.getServerPort() + "/uploadFile/" + fileUUID;
        Map<String,Object> map1 = new HashMap<>();
        map1.put("name",originalFilename);
        map1.put("url",url);

        //文档url存入数据库
        FilePojo filePojo = new FilePojo();
        filePojo.setCaseNumber(caseNumber);
        filePojo.setFileType(17);
        filePojo.setFileUrl(url);
        fileDao.insert(filePojo);
        //更新上下颌步数
        caseDao.update(null, new LambdaUpdateWrapper<CasePojo>().eq(CasePojo::getCaseNumber,caseNumber)
                .set(CasePojo::getUpperTotalStep,upperSteps)
                .set(CasePojo::getLowerTotalStep,lowerSteps));

        //新增track
        TrackPojo newTrack = new TrackPojo();
        newTrack.setCaseNumber(caseNumber);
        newTrack.setStatus(109);
        newTrack.setStatusName(UserConstant.TRACK.STATUS109);
        trackDao.insert(newTrack);
        return Result.success(map1);
    }


    /*
    * 查看方案文档
    * */
    @RequestMapping(value = "/schemeFile/{caseNumber}",method = RequestMethod.GET)
    public Result getSchemeFile(@PathVariable("caseNumber") Long caseNumber){
        FilePojo filePojo = fileDao.selectOne(new LambdaQueryWrapper<FilePojo>().eq(FilePojo::getCaseNumber, caseNumber));
//        Map<String,Object> map = new HashMap<>();
//        map.put("file",filePojo);
//        CasePojo casePojo = caseDao.selectOne(new LambdaQueryWrapper<CasePojo>().eq(CasePojo::getCaseNumber, caseNumber));
        return Result.success(filePojo);
    }

    /*
    * 审核方案
    * */
    @RequestMapping(value = "examineScheme",method = RequestMethod.PUT)
    public Result examineScheme(@RequestBody Map<String,Object> map){
        Long caseNumber = (Long) map.get("caseNumber");
        Integer isPass = (Integer) map.get("isPass");
        String remark = (String) map.get("remark");

        LambdaUpdateWrapper<FilePojo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(FilePojo::getCaseNumber,caseNumber).eq(FilePojo::getFileType,17)
                .set(FilePojo::getIsPass,isPass)
                .set(FilePojo::getRemark,remark);
        fileDao.update(null,wrapper);
        if (isPass == 0){
            caseDao.update(null,new LambdaUpdateWrapper<CasePojo>().eq(CasePojo::getCaseNumber,caseNumber)
                    .set(CasePojo::getCaseState,6)
                    .set(CasePojo::getStateInfo,"方案已驳回\n("+remark+")"));
            //新增track

        } else {
            CasePojo casePojo = caseDao.selectOne(new LambdaQueryWrapper<CasePojo>().eq(CasePojo::getCaseNumber, caseNumber));
            caseDao.update(null,new LambdaUpdateWrapper<CasePojo>().eq(CasePojo::getCaseNumber,caseNumber)
                    .set(CasePojo::getCaseState,9)
                    .set(CasePojo::getStateInfo,"(U:"+casePojo.getUpperTotalStep()+"/L:"+casePojo.getLowerTotalStep()+")"));
        }
        return Result.success();
    }

}

