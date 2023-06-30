package com.zhu.casemanage.service;

import com.zhu.casemanage.dao.FileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl {
    @Autowired
    private FileDao fileDao;

}
