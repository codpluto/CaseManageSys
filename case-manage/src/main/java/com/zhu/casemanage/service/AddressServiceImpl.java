package com.zhu.casemanage.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhu.casemanage.constant.UserConstant;
import com.zhu.casemanage.dao.AddressDao;
import com.zhu.casemanage.dao.UserDao;
import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.pojo.AddressPojo;
import com.zhu.casemanage.pojo.UserPojo;
import com.zhu.casemanage.utils.JwtUtil;
import com.zhu.casemanage.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl {
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserDao userDao;

    public List<AddressPojo> findUserAddressList(int userId){
        List<AddressPojo> addressPojos = addressDao.selectList(new QueryWrapper<AddressPojo>().eq("user_id", userId));
        return addressPojos;
    }

    public void addAddress(AddressPojo newAddress){
        addressDao.insert(newAddress);
    }

    /*
    * 根据token查询当前登录用户的地址列表
    * */
    public List<AddressPojo> getSysUserAddressList(String token){
        String account = jwtUtil.parseToken(token);
        String tokenCache = (String) redisUtil.get(UserConstant.getTokenKey(account));
        if (tokenCache == null){
            throw new BusinessException("token已过期");
        } else if (!tokenCache.equals(token)){
            throw new BusinessException("token已过期");
        }
        UserPojo userPojo = userDao.selectOne(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getAccount, account));
        List<AddressPojo> addressPojos = addressDao.selectList(new LambdaQueryWrapper<AddressPojo>().eq(AddressPojo::getUserId, userPojo.getUserId()));
        return addressPojos;
    }

}
