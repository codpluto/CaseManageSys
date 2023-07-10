package com.zhu.casemanage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zhu.casemanage.dao.CaseDao;
import com.zhu.casemanage.dao.SendDao;
import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.pojo.CasePojo;
import com.zhu.casemanage.pojo.PreferPojo;
import com.zhu.casemanage.pojo.SendPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class SendServiceImpl {
    @Autowired
    private SendDao sendDao;

    @Autowired
    private CaseDao caseDao;

    /*
    * 根据病例号获取发货信息
    * */
    public List<SendPojo> getSendListByNumber(Long caseNumber){
        return sendDao.selectList(new QueryWrapper<SendPojo>().eq("case_number", caseNumber));
    }

    /*
     * 根据病例号更新发货信息
     * */
    public void updateSendByNumber(Long caseNumber,@RequestBody SendPojo sendInfo){
        if (sendDao.update(sendInfo,new QueryWrapper<SendPojo>().eq("case_number",caseNumber)) == 0){
            throw new BusinessException("发货记录不存在");
        }
    }

//    /*
//    * 根据病例号提交快递信息
//    * */
//    public void updateCaseExpressByCaseNumber(SendPojo newExpress){
////        UpdateWrapper<CasePojo> updateWrapper = new UpdateWrapper<>();
////        updateWrapper.eq("case_number",caseNumber);
////        updateWrapper.set("express_id",expressId);
////        updateWrapper.set("express_num",expressNum);
//
//        if (caseDao.update() == 0){
//            throw new BusinessException("病例不存在");
//        }
//    }

    /*
    * 提交快递信息
    * */
    public void addCaseExpress(SendPojo newExpress){
        sendDao.insert(newExpress);
    }

    /*
    * 根据病例号查询快递信息
    * */
    public SendPojo getCaseExpressByCaseNumber(Long caseNumber){
        return sendDao.selectOne(new QueryWrapper<SendPojo>().eq("case_number",caseNumber)
                .eq("express_type",1));
    }

    /*
     * 根据病例号查询发货信息
     * */
    public List<SendPojo> getSendListByCaseNumber(Long caseNumber){
        return sendDao.selectList(new QueryWrapper<SendPojo>().eq("case_number",caseNumber)
                .eq("express_type",2));
    }
}
