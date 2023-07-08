package com.zhu.casemanage.controller;

import com.aeert.jfilter.annotation.MoreSerializeField;
import com.aeert.jfilter.annotation.SerializeField;
import com.zhu.casemanage.pojo.CasePojo;
import com.zhu.casemanage.pojo.FilePojo;
import com.zhu.casemanage.service.FileServiceImpl;
import com.zhu.casemanage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/platform/cmFileInfo")
public class FileInfoController {

    @Autowired
    private FileServiceImpl fileService;

    /*
     * 上传数字模型或文件压缩包
     * */
    @RequestMapping(value = "/cmCaseInfo/stl",method = RequestMethod.POST)
    public Result uploadStl(@RequestBody FilePojo stl) {
        fileService.addFile(stl);
        return Result.success(stl.getFileUrl());
    }

    /*
     * 根据病例号获取该病例的文件信息（数字模型或文件压缩包）
     * */
    @RequestMapping(value = "/{caseNumber}/file",method = RequestMethod.GET)
    @MoreSerializeField({
            @SerializeField(clazz = CasePojo.class, includes = {"fileName","fileUrl","fileType"}),
    })
    public Result getFileByCaseNumber(@PathVariable("caseNumber") Long caseNumber) {
        List<FilePojo> fileList = fileService.getStlListByNumber(caseNumber);
        return Result.success(fileList);
    }

    /*
     * 根据病例号获取其映像资料
     * */
    @RequestMapping(value = "/{caseNumber}/image",method = RequestMethod.GET)
    @MoreSerializeField({
            @SerializeField(clazz = CasePojo.class, includes = {"fileName","fileUrl","fileType"}),
    })
    public Result getImageByCaseNumber(@PathVariable("caseNumber") Long caseNumber) {
        List<FilePojo> imageList = fileService.getImageListByNumber(caseNumber);
        return Result.success(imageList);
    }

    /*
     * 上传照片
     * */
    @RequestMapping(value = "/cmCaseInfo/record",method = RequestMethod.POST)
    public Result addCaseImage(@RequestBody FilePojo img){
        fileService.addFile(img);
        return Result.success();
    }

}
