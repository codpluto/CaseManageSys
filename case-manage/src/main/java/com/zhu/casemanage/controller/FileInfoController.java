package com.zhu.casemanage.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhu.casemanage.constant.UserConstant;
import com.zhu.casemanage.dao.FileDao;
import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.pojo.FilePojo;
import com.zhu.casemanage.pojo.SendPojo;
import com.zhu.casemanage.pojo.TrackPojo;
import com.zhu.casemanage.service.CaseServiceImpl;
import com.zhu.casemanage.service.FileServiceImpl;
import com.zhu.casemanage.service.TrackServiceImpl;
import com.zhu.casemanage.utils.Constant;
import com.zhu.casemanage.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Slf4j
@RequestMapping(value = "/platform/cmFileInfo")
public class FileInfoController {

    @Autowired
    private FileServiceImpl fileService;
    @Autowired
    private TrackServiceImpl trackService;
    @Autowired
    private CaseServiceImpl caseService;
    @Autowired
    private FileDao fileDao;


    @Value("${file-save-path}")
    private String fileSavePath;

    /**
     * 文件上传接口
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result upload(@RequestParam MultipartFile file,HttpServletRequest req) throws IOException {
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
        Map<String,Object> map = new HashMap<>();
        map.put("name",originalFilename);
        map.put("url",url);

        return Result.success(map);
    }


//    @GetMapping("/download")
////请求匹配到方法
//    public void download(String name, HttpServletResponse response) {
//        //通过输入流读取文件内容
//        try {
//            FileInputStream fileInputStream = new FileInputStream(fileSavePath + name);
//            //通过输出流，将文件写回到浏览器，在浏览器展示图片
//            ServletOutputStream outputStream = response.getOutputStream();
//
//            response.setContentType("image/jpg");//设置响应回去的是什么类型的文件
//
//            int length = 0;       //   i/o读取 重点复习
//            byte[] bytes = new byte[1024];
//            while (((length = fileInputStream.read(bytes)) != -1)) {
//                outputStream.write(bytes, 0, length);
//                outputStream.flush();
//            }
//
//            //关闭资源
//            outputStream.close();
//            fileInputStream.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    /*
     * 上传数字模型或文件压缩包或照片
     * */
    @RequestMapping(value = "/caseInfo/file",method = RequestMethod.POST)
    public Result uploadFile(@RequestBody FilePojo newFile) {
//        newStl.setFileType();
        Map<String, Object> map = fileService.addFile(newFile);
        Integer isNew = (Integer) map.get("isNew");
        if (isNew == 1){
            if (newFile.getFileType() >= 14 && newFile.getFileType() <= 16){
                TrackPojo track = trackService.getTrackByStatus(newFile.getCaseNumber(), 103);
                if (track == null){
                    TrackPojo newTrack = new TrackPojo();
                    newTrack.setCaseNumber(newFile.getCaseNumber());
                    newTrack.setStatus(103);
                    newTrack.setStatusName(UserConstant.TRACK.STATUS103);
                    trackService.addTrack(newTrack);
                    if (caseService.getCaseByNumber(newFile.getCaseNumber()).getCaseState() < 2){
                        caseService.updateCaseState(newFile.getCaseNumber(), 2);
                    }
                }
            }
        } else {
            String fileUrl = (String) map.get("fileUrl");
            File delFile = new File(fileSavePath + fileUrl.split("/")[4]);
            if (delFile.exists()) {
                if (delFile.delete()){
                    log.info("===============删除成功==================");
                } else {
                    log.info("===============删除失败=================");
                }
            } else {
                log.info("===============删除失败=================");
            }
        }
        if (newFile.getFileType() == 1){
            caseService.updateCaseFacePhoto(newFile.getCaseNumber(), newFile.getFileUrl());
        }
        return Result.success();
    }


    /*
     * 根据病例号获取该病例的文件信息（数字模型或文件压缩包）
     * */
    @RequestMapping(value = "/{caseNumber}/file",method = RequestMethod.GET)
    public Result getFileListByCaseNumber(@PathVariable("caseNumber") Long caseNumber) {
        List<FilePojo> fileList = fileService.getFileListByCaseNumber(caseNumber);
        if (fileList.size() == 0){
            return Result.success();
        }
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
        if (imageList.size() > 0){
            return Result.success(imageList);
        }
        return Result.success();
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
