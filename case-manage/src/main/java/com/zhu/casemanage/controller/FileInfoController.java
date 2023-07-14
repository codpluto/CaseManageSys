package com.zhu.casemanage.controller;


import com.zhu.casemanage.pojo.FilePojo;
import com.zhu.casemanage.pojo.SendPojo;
import com.zhu.casemanage.pojo.TrackPojo;
import com.zhu.casemanage.service.CaseServiceImpl;
import com.zhu.casemanage.service.FileServiceImpl;
import com.zhu.casemanage.service.TrackServiceImpl;
import com.zhu.casemanage.utils.Constant;
import com.zhu.casemanage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/platform/cmFileInfo")
public class FileInfoController {

    @Autowired
    private FileServiceImpl fileService;
    @Autowired
    private TrackServiceImpl trackService;
    @Autowired
    private CaseServiceImpl caseService;

    /*
     * 上传数字模型或文件压缩包或照片
     * */
    @RequestMapping(value = "/caseInfo/file",method = RequestMethod.POST)
    public Result uploadFile(@RequestBody FilePojo newFile) {
//        newStl.setFileType();
        fileService.addFile(newFile);
        if (newFile.getFileType() == 1){
            caseService.updateCaseFacePhoto(newFile.getCaseNumber(), newFile.getFileUrl());
        }
        if (newFile.getFileType() >= 14 && newFile.getFileType() <= 16){
            TrackPojo track = trackService.getTrackByStatus(newFile.getCaseNumber(), 103);
            if (track == null){
                TrackPojo newTrack = new TrackPojo();
                newTrack.setCaseNumber(newFile.getCaseNumber());
                newTrack.setStatus(103);
                trackService.addTrack(newTrack);
                if (caseService.getCaseByNumber(newFile.getCaseNumber()).getCaseState() < 2){
                    caseService.updateCaseState(newFile.getCaseNumber(), 2);
                }
            }
        }
        return Result.success(newFile.getFileUrl());
    }

    /*
     * 根据病例号获取该病例的文件信息（数字模型或文件压缩包）
     * */
    @RequestMapping(value = "/{caseNumber}/file",method = RequestMethod.GET)
    public Result getFileListByCaseNumber(@PathVariable("caseNumber") Long caseNumber) {
        List<FilePojo> fileList = fileService.getFileListByCaseNumber(caseNumber);
        List<Map<String, Object>> list = new ArrayList<>();
        for (FilePojo file:
             fileList) {
            Map<String, Object> map = new HashMap<>();
            map.put("fileName",file.getFileName());
            map.put("fileType",file.getFileType());
            map.put("fileUrl",file.getFileUrl());
            list.add(map);
        }
        return Result.success(list);
    }

    /*
     * 根据病例号获取其映像资料
     * */
    @RequestMapping(value = "/{caseNumber}/image",method = RequestMethod.GET)
    public Result getImageByCaseNumber(@PathVariable("caseNumber") Long caseNumber) {
        List<FilePojo> imageList = fileService.getImageListByNumber(caseNumber);

        return Result.success(imageList);
    }

    /*
     * 上传照片
     * */
    @RequestMapping(value = "/caseInfo/record",method = RequestMethod.POST)
    public Result addCaseImage(@RequestBody FilePojo img){
        fileService.addFile(img);
        return Result.success();
    }

    /*
    * 根据病例号和文件类型删除文件
    * */
    @RequestMapping(value = "/caseNumber/{caseNumber}/fileType/{fileType}",method = RequestMethod.DELETE)
    public Result delCaseImage(@PathVariable("caseNumber") Long caseNumber,
                               @PathVariable("fileType") int fileType){
        fileService.delFileByType(caseNumber,fileType);
        if (fileType == 1){
            caseService.updateCaseFacePhoto(caseNumber, Constant.FACEPHOTO);
        }
        return Result.success();
    }
}
