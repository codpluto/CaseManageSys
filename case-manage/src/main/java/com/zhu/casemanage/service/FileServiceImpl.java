package com.zhu.casemanage.service;

import com.zhu.casemanage.dao.FileDao;
import com.zhu.casemanage.pojo.FilePojo;
import com.zhu.casemanage.pojo.SendPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class FileServiceImpl {
    @Autowired
    private FileDao fileDao;

    public void addFile(@RequestBody FilePojo newFile){

    }
}
