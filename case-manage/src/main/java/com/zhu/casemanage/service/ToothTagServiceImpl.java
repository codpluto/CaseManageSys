package com.zhu.casemanage.service;

import com.zhu.casemanage.dao.ToothTagDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToothTagServiceImpl {
    @Autowired
    private ToothTagDao toothTagDao;
}
