package com.zhu.casemanage.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zhu.casemanage.constant.UserConstant;
import com.zhu.casemanage.dao.PreferDao;
import com.zhu.casemanage.dao.UserDao;
import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.pojo.PreferPojo;
import com.zhu.casemanage.pojo.UserPojo;
import com.zhu.casemanage.utils.JwtUtil;
import com.zhu.casemanage.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PreferServiceImpl {
    @Autowired
    private PreferDao preferDao;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserDao userDao;

    /**
     * 新增偏好
     */
    public void addPrefer(PreferPojo newPrefer,String token){
        String account = jwtUtil.parseToken(token);
        String tokenCache = (String) redisUtil.get(UserConstant.getTokenKey(account));
        if (tokenCache == null){
            throw new BusinessException("token已过期");
        } else if (!tokenCache.equals(token)){
            throw new BusinessException("token已过期");
        }
        UserPojo userPojo = userDao.selectOne(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getAccount, account));
        newPrefer.setUserId(userPojo.getUserId());
        preferDao.insert(newPrefer);
    }

    /**
     * 修改偏好设定
     */
    public void updatePrefer(PreferPojo newPrefer,String token){
        String account = jwtUtil.parseToken(token);
        String tokenCache = (String) redisUtil.get(UserConstant.getTokenKey(account));
        if (tokenCache == null){
            throw new BusinessException("token已过期");
        } else if (!tokenCache.equals(token)){
            throw new BusinessException("token已过期");
        }
        UserPojo userPojo = userDao.selectOne(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getAccount, account));
        newPrefer.setUserId(userPojo.getUserId());
        if (preferDao.updateById(newPrefer) == 0){
            throw new BusinessException("偏好不存在");
        }
    }

    /**
     * 删除偏好
     */
    public void delPrefer(Integer preferId){
        if (preferDao.deleteById(preferId) == 0){
            throw new BusinessException("偏好不存在");
        }
    }

    /**
     * 根据Id查询偏好
     */
    public PreferPojo getPreferById(Integer preferId){
        PreferPojo preferPojo = preferDao.selectById(preferId);
        if (preferPojo == null){
            throw new BusinessException("偏好不存在");
        }
        return preferPojo;
    }

    /**
     * 根据userId查询偏好列表
     */
    public List<PreferPojo> getPerferListByToken(String token){
        String account = jwtUtil.parseToken(token);
        String tokenCache = (String) redisUtil.get(UserConstant.getTokenKey(account));
        if (tokenCache == null){
            throw new BusinessException("token已过期");
        } else if (!tokenCache.equals(token)){
            throw new BusinessException("token已过期");
        }
        UserPojo userPojo = userDao.selectOne(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getAccount, account));
        List<PreferPojo> preferList = preferDao.selectList(new QueryWrapper<PreferPojo>().eq("user_id", userPojo.getUserId()));
        return preferList;
    }

    /*
    * 设置偏好名称
    * */
    public void setPreinstallName(Integer preferId,String preinstall){
//        PreferPojo newPrefer = preferDao.selectOne(new QueryWrapper<PreferPojo>().eq("id", prefer.getPreferId()));
//        newPrefer.setPreinstall(prefer.getPreinstall());
//        return newPrefer;
        UpdateWrapper<PreferPojo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",preferId);
        updateWrapper.set("preinstall",preinstall);
        if (preferDao.update(null,updateWrapper) == 0){
            throw new BusinessException("病例不存在");
        }
    }
}
