package com.zhu.casemanage.service;

import com.zhu.casemanage.dao.CaseTrackDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaseTrackServiceImpl {
    @Autowired
    private CaseTrackDao caseTrackDao;
}
