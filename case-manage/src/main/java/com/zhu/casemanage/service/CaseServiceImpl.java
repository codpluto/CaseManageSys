package com.zhu.casemanage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zhu.casemanage.dao.CaseDao;
import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.pojo.CasePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CaseServiceImpl {
    @Autowired
    private CaseDao caseDao;

    //根据病例号获取病例信息
    public CasePojo findCaseByNumber(Long caseNumber){
        CasePojo caseInfo = caseDao.selectOne(new QueryWrapper<CasePojo>().eq("case_number", caseNumber));
        if (caseInfo == null){
            throw new BusinessException("该病例不存在");
        }

        return caseInfo;
    }

    public CasePojo findCaseById(int caseId){
        CasePojo casePojo = caseDao.selectById(caseId);
        if (casePojo == null){
            throw new BusinessException("该病例不存在");
        }
        return casePojo;
    }

    //新增病例
    public void addCase(@RequestBody CasePojo casePojo){
//        casePojo.setCaseNumber(snowFlake.nextId());
        try {
            caseDao.insert(casePojo);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    //根据病例号删除病例
    public void delCase(Long caseNumber){
        if (caseDao.delete(new QueryWrapper<CasePojo>().eq("case_number", caseNumber)) == 0){
            throw new BusinessException("病例不存在，删除失败");
        }
    }

    public void delKeepCase(Long caseNumber){
        UpdateWrapper<CasePojo> updateWrapper = new UpdateWrapper<CasePojo>();
        updateWrapper.eq("case_number",caseNumber);
        updateWrapper.set("is_valid",0);
        if (caseDao.update(null,updateWrapper) == 0){
            throw new BusinessException("病例不存在，删除失败");
        }
//        CasePojo casePojo = caseDao.selectOne(new QueryWrapper<CasePojo>().eq("case_number", caseNumber));
//        if (casePojo == null){
//            throw new BusinessException("病例不存在");
//        }
    }

    //跟新病例信息
    public void updateCase(CasePojo newCase){
        if (caseDao.update(newCase,new QueryWrapper<CasePojo>().eq("case_number",newCase.getCaseNumber())) == 0){
            throw new BusinessException("病例不存在，修改失败");
        }
    }

}
