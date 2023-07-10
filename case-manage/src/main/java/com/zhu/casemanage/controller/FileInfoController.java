package com.zhu.casemanage.controller;

import com.aeert.jfilter.annotation.MoreSerializeField;
import com.aeert.jfilter.annotation.SerializeField;
import com.zhu.casemanage.pojo.FilePojo;
import com.zhu.casemanage.pojo.SendPojo;
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
     * 上传文件（数字模型、文件压缩包、影像照片）
     * */
    @RequestMapping(value = "/caseInfo/file",method = RequestMethod.POST)
    public Result uploadStl(@RequestBody FilePojo newFile) {
        fileService.addFile(newFile);
        return Result.success();
    }

    /*
     * 根据病例号获取该病例的文件信息（数字模型或文件压缩包）
     * */
    @RequestMapping(value = "/{caseNumber}/file",method = RequestMethod.GET)
    @MoreSerializeField({
            @SerializeField(clazz = FilePojo.class, includes = {"fileName","fileType","fileUrl"}),
    })
    public Result getFileListByCaseNumber(@PathVariable("caseNumber") Long caseNumber) {
        List<FilePojo> fileList = fileService.getFileListByCaseNumber(caseNumber);
        return Result.success(fileList);
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
//    @RequestMapping(value = "/caseInfo/record",method = RequestMethod.POST)
//    public Result addCaseImage(@RequestBody FilePojo img){
//        fileService.addFile(img);
//        return Result.success();
//    }

    /*
    * 删除照片
    * */
    @RequestMapping(value = "/caseNumber/{caseNumber}/fileType/{fileType}",method = RequestMethod.DELETE)
    public Result delCaseImage(@PathVariable("caseNumber") Long caseNumber,
                               @PathVariable("fileType") int fileType){
        fileService.delFileByType(caseNumber,fileType);
        return Result.success();
    }
}
