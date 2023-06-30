package com.zhu.casemanage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhu.casemanage.pojo.UserPojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<UserPojo> {


}
