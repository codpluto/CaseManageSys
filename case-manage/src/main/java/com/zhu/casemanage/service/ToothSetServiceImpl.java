package com.zhu.casemanage.service;

import com.zhu.casemanage.dao.ToothSetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToothSetServiceImpl {
    @Autowired
    private ToothSetDao toothSetDao;
}
