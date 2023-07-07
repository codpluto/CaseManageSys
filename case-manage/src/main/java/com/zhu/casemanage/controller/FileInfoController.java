package com.zhu.casemanage.controller;

import com.zhu.casemanage.pojo.FilePojo;
import com.zhu.casemanage.service.FileServiceImpl;
import com.zhu.casemanage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/platform/cmFileInfo")
public class FileInfoController {

    @Autowired
    private FileServiceImpl fileService;

    /*
     * 上传数字模型或文件压缩包
     * */
    @RequestMapping(value = "/cmCaseInfo/stl",method = RequestMethod.POST)
    public Result uploadStl() {
        return new Result();
    }

    /*
     * 根据病例号获取该病例的文件信息（数字模型或文件压缩包）
     * */
    @RequestMapping(value = "/{caseNumber}/file",method = RequestMethod.PUT)
    public Result getFileByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
        return new Result();
    }

    /*
     * 根据病例号获取其映像资料
     * */
    @RequestMapping(value = "/{caseNumber}/image",method = RequestMethod.PUT)
    public Result getImageByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
        return new Result();
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
