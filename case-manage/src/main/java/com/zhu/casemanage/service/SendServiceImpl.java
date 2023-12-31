package com.zhu.casemanage.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zhu.casemanage.dao.CaseDao;
import com.zhu.casemanage.dao.SendDao;
import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.pojo.CasePojo;
import com.zhu.casemanage.pojo.SendPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    * 根据病例号查询最近一次发货信息
    * */
    public List<SendPojo> CaseExpressDesc(Long caseNumber){
        QueryWrapper<SendPojo> wrapper = new QueryWrapper<>();
        wrapper.eq("case_number",caseNumber).
                orderByDesc("create_time");
        List<SendPojo> sendList = sendDao.selectList(wrapper);
        if (sendList.size() == 0){
            throw new BusinessException("该病例无发货记录");
        }
        return sendList;
    }

    /*
    * 更新牙模寄出的快递信息
    * */
    public void updateCaseExpress(SendPojo newSend){
        UpdateWrapper<SendPojo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("case_number",newSend.getCaseNumber())
                .eq("express_type",1)
                .set("express_id",newSend.getExpressId())
                .set("express_num",newSend.getExpressNum());
        if (sendDao.update(null,updateWrapper) == 0){
            throw new BusinessException("快递信息不存在");
        }
    }


    /*
     * 根据病例号查询发货信息
     * */
    public List<SendPojo> getSendListByCaseNumber(Long caseNumber){
        return sendDao.selectList(new QueryWrapper<SendPojo>().eq("case_number",caseNumber)
                .eq("express_type",2));
    }

    /*
    * 获取最近一次生产记录
    * */
    public SendPojo getLastProduce(Long caseNumber){
        List<SendPojo> sendPojos = sendDao.selectList(new LambdaQueryWrapper<SendPojo>().eq(SendPojo::getCaseNumber, caseNumber)
                .orderByDesc(SendPojo::getCreateTime));
        if (sendPojos.size() == 0){
            throw new BusinessException("无生产记录！");
        }
        return sendPojos.get(0);
    }

    /*
    * 判断是否是第一次生产
    * */
    public Integer isFirstProduce(Long caseNumber){
        Long count = sendDao.selectCount(new LambdaQueryWrapper<SendPojo>().eq(SendPojo::getCaseNumber, caseNumber));
        if (count > 0){
            return 0;
        } else {
            return 1;
        }
    }

    /*
    * 生产需要的字段
    * */
    public Map<String,Object> produce(Long caseNumber){
        Map<String,Object> map = new HashMap<>();
        Integer firstProduce = isFirstProduce(caseNumber);
        CasePojo casePojo = caseDao.selectById(caseNumber);
        map.put("isFirstProduce",firstProduce);
        map.put("lowerSentStep",casePojo.getLowerSentStep());
        map.put("lowerTotalStep",casePojo.getLowerTotalStep());
        map.put("upperSentStep",casePojo.getUpperSentStep());
        map.put("upperTotalStep",casePojo.getUpperTotalStep());
        return map;
    }

}
