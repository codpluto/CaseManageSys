package com.zhu.casemanage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zhu.casemanage.dao.TrackDao;
import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.pojo.SendPojo;
import com.zhu.casemanage.pojo.TrackPojo;
import com.zhu.casemanage.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackServiceImpl {
    @Autowired
    private TrackDao trackDao;

    /*
    * 新增病例记录
    * */
    public void addTrack(TrackPojo newTrack){
        trackDao.insert(newTrack);
    }

    /*
    * 更新牙模寄出记录
    * */
    public void updateCaseExpress(SendPojo newSend){
        UpdateWrapper<TrackPojo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("case_number",newSend.getCaseNumber())
                .eq("status",102)
                .set("remark", Constant.EXPRESS.get(newSend.getExpressId())+" "+newSend.getExpressNum());
        if (trackDao.update(null,updateWrapper) == 0){
            throw new BusinessException("该病例记录不存在");
        }
    }

    /*
    * 根据病例号和状态查询病例记录
    * */
    public TrackPojo getTrackByStatus(Long caseNumber,int status){
        return trackDao.selectOne(new QueryWrapper<TrackPojo>().eq("case_number",caseNumber)
                .eq("status",status));
    }

}
