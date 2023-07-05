package com.zhu.casemanage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhu.casemanage.dao.SendDao;
import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.pojo.SendPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class SendServiceImpl {
    @Autowired
    private SendDao sendDao;

    /*
    * 根据病例号获取发货信息
    * */
    public SendPojo findSendByNumber(long caseNumber){
        return sendDao.selectOne(new QueryWrapper<SendPojo>().eq("case_number", caseNumber));
    }

    /*
     * 根据病例号更新发货信息
     * */
    public void updateSendByNumber(long caseNumber,@RequestBody SendPojo sendInfo){
        if (sendDao.update(sendInfo,new QueryWrapper<SendPojo>().eq("case_number",caseNumber)) == 0){
            throw new BusinessException("发货记录不存在");
        }
    }

}
