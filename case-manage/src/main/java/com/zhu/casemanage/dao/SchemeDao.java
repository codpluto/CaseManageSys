package com.zhu.casemanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhu.casemanage.pojo.SchemePojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SchemeDao extends BaseMapper<SchemePojo> {
}
