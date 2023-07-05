package com.zhu.casemanage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhu.casemanage.constant.UserConstant;
import com.zhu.casemanage.dao.UserDao;
import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.pojo.UserPojo;
import com.zhu.casemanage.utils.JwtUtil;
import com.zhu.casemanage.utils.RedisUtil;
import com.zhu.casemanage.utils.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl {
    @Autowired
    private UserDao userDao;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RedisUtil redisUtil;


    //登录
    public UserPojo findUserByAccount(String account){
        UserPojo user = userDao.selectOne(new QueryWrapper<UserPojo>().eq("account", account));
        return user;
    }


    public void addUser(@RequestBody UserPojo newUser){
        if (userDao.selectOne(new QueryWrapper<UserPojo>().eq("account",newUser.getAccount())) != null){
            throw new BusinessException("账号已存在");
        }
        userDao.insert(newUser);
    }

    public String login(String account,String password){
        UserPojo user = this.findUserByAccount(account);
        if (user == null){
//            return Result.failed("账号或密码错误");
            throw new BusinessException("用户不存在");
        }

        if (!user.getPassword().equals(password)){
            throw new BusinessException("密码错误");
        }
        //登录成功，生产token
        log.info("login success,account={}",account);
        String token = jwtUtil.createToken(user.getAccount());
        //redis存token,2h有效期
        redisUtil.set(UserConstant.getTokenKey(user.getAccount()),token);
        return token;
    }

    /**
     *
     * 根据查询用户信息
     */
    public UserPojo getUserInfoById(int userId){
        UserPojo userPojo = userDao.selectById(userId);
        if (userPojo == null){
            throw new BusinessException("用户不存在");
        }
        return userPojo;
    }

    /*
    * 删除用户
    * */

}
