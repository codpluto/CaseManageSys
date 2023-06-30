package com.zhu.casemanage.service;

import com.zhu.casemanage.dao.UserDao;
import com.zhu.casemanage.pojo.UserPojo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserServiceImpl {
    @Autowired
    private UserDao userDao;

    public void addUser(String user_name,String password,int user_type,String user_face,String user_phone,String user_email){
//        userDao.insert(new UserPojo(0,user_name,password,user_type,user_face,user_phone,user_email));
        UserPojo user = new UserPojo();

    }


}
