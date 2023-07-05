package com.zhu.casemanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhu.casemanage.pojo.TrackPojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TrackDao extends BaseMapper<TrackPojo> {
}
