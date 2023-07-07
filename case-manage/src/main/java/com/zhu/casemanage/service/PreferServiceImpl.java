package com.zhu.casemanage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zhu.casemanage.dao.PreferDao;
import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.pojo.PreferPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PreferServiceImpl {
    @Autowired
    PreferDao preferDao;

    /**
     * 新增偏好
     */
    public void addPrefer(@RequestBody PreferPojo newPrefer){
        preferDao.insert(newPrefer);
    }

    /**
     * 修改偏好设定
     */
    public void updatePrefer(@RequestBody PreferPojo newPrefer){
        if (preferDao.update(newPrefer,new QueryWrapper<PreferPojo>().eq("id",newPrefer.getPreferId())) == 0){
            throw new BusinessException("偏好不存在");
        }
    }

    /**
     * 删除偏好
     */
    public void delPrefer(int preferId){
        if (preferDao.deleteById(preferId) == 0){
            throw new BusinessException("偏好不存在");
        }
    }

    /**
     * 根据Id查询偏好
     */
    public PreferPojo findPreferById(int preferId){
        PreferPojo preferPojo = preferDao.selectOne(new QueryWrapper<PreferPojo>().eq("id", preferId));
        if (preferPojo == null){
            throw new BusinessException("偏好不存在");
        }
        return preferPojo;
    }

    /**
     * 根据userId查询偏好列表
     */
    public List<PreferPojo> getPerferList(int userId){
        List<PreferPojo> preferList = preferDao.selectList(new QueryWrapper<PreferPojo>().eq("user_id", userId));
        return preferList;
    }

    /*
    * 设置偏好名称
    * */
    public void setPreinstallName(int preferId,String preinstall){
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
