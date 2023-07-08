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

    public List<FilePojo> getStlListByNumber(Long caseNumber){
        List<FilePojo> stlList = fileDao.selectList(new QueryWrapper<FilePojo>().eq("case_number", caseNumber)
                .between("file_type",14,16));
        return stlList;
    }


}
