package com.zhu.casemanage.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zhu.casemanage.dao.FileDao;
import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.pojo.FilePojo;
import com.zhu.casemanage.pojo.SendPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl {
    @Autowired
    private FileDao fileDao;

//    /**
//     * 根据MD5查询文件
//     * @param md5
//     * @return
//     */
//    public FilePojo getFileByMd5(String md5) {
//        LambdaQueryWrapper<FilePojo> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(FilePojo::getMd5, md5);
//        List<FilePojo> list = fileDao.selectList(queryWrapper);
//        return list.size() == 0 ? null : list.get(0);
//    }


    /*
     * 添加文件
     * */
    public Map<String,Object> addFile(FilePojo newFile){
        FilePojo filePojo = fileDao.selectOne(new LambdaQueryWrapper<FilePojo>().eq(FilePojo::getCaseNumber, newFile.getCaseNumber()).eq(FilePojo::getFileType, newFile.getFileType()));
        Map<String ,Object> map = new HashMap<>();
        if (filePojo == null){
            map.put("isNew",1);
            fileDao.insert(newFile);
        } else {
            LambdaUpdateWrapper<FilePojo> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(FilePojo::getFileType, newFile.getFileType()).eq(FilePojo::getCaseNumber, newFile.getCaseNumber())
                    .set(FilePojo::getFileName,newFile.getFileName())
                    .set(FilePojo::getFileUrl,newFile.getFileUrl());
            fileDao.update(null,wrapper);
            map.put("isNew",0);
            map.put("fileUrl",filePojo.getFileUrl());
        }
        return map;
    }

    /*
    * 根据病例号查询图片列表
    * */
    public List<FilePojo> getImageListByNumber(Long caseNumber){
        List<FilePojo> imageList = fileDao.selectList(new QueryWrapper<FilePojo>().eq("case_number", caseNumber)
                .between("file_type",1,13));
//        if (imageList.size() == 0){
//            throw new BusinessException("该病例未上传照片");
//        }
        return imageList;
    }

    /*
    * 根据文件id删除文件
    * */
    public void delFile(Integer fileId){
        if (fileDao.deleteById(fileId) == 0){
            throw new BusinessException("文件不存在");
        }
    }

    /*
    * 根据病例号和文件类型删除文件
    * */
    public void delFileByType(Long caseNumber,int fileType){
        if (fileDao.delete(new QueryWrapper<FilePojo>().eq("case_number",caseNumber)
                .eq("file_type",fileType)) == 0){
            throw new BusinessException("文件不存在");
        }
    }

    /*
    * 根据病例号查询数字模型和压缩包或照片
    * */
    public List<FilePojo> getFileListByCaseNumber(Long caseNumber){
        List<FilePojo> fileList = fileDao.selectList(new QueryWrapper<FilePojo>().eq("case_number", caseNumber));
//        if (fileList.size() == 0){
//            throw new BusinessException("文件不存在");
//        }
        return fileList;
    }



}
