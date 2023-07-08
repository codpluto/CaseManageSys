package com.zhu.casemanage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhu.casemanage.dao.FileDao;
import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.pojo.FilePojo;
import com.zhu.casemanage.pojo.SendPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class FileServiceImpl {
    @Autowired
    private FileDao fileDao;

    /*
     * 添加文件
     * */
    public void addFile(FilePojo newFile){
        fileDao.insert(newFile);
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
    * 根据病例号查询数字模型和压缩包
    * */
    public List<FilePojo> getFileListByCaseNumber(Long caseNumber){
        List<FilePojo> fileList = fileDao.selectList(new QueryWrapper<FilePojo>().eq("case_number", caseNumber)
                .between("file_type",14,16));
        if (fileList.size() == 0){
            throw new BusinessException("文件不存在");
        }
        return fileList;
    }



}
