package com.zhu.casemanage.service;

import com.zhu.casemanage.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl {
    @Autowired
    UserDao userDao;


}
