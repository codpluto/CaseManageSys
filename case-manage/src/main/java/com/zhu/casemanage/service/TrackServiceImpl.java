package com.zhu.casemanage.service;

import com.zhu.casemanage.dao.TrackDao;
import com.zhu.casemanage.pojo.TrackPojo;
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




}
