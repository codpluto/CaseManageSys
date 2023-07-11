package com.zhu.casemanage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zhu.casemanage.dao.CaseDao;
import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.pojo.CasePojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
public class CaseServiceImpl {
    @Autowired
    private CaseDao caseDao;

    //根据病例号获取病例信息
    public CasePojo getCaseByNumber(Long caseNumber){
//        CasePojo caseInfo = caseDao.selectOne(new QueryWrapper<CasePojo>().eq("case_number", caseNumber));
        CasePojo caseInfo = caseDao.selectById(caseNumber);
        if (caseInfo == null){
            throw new BusinessException("该病例不存在");
        }
        return caseInfo;
    }

//    public CasePojo getCaseById(int caseId){
//        CasePojo casePojo = caseDao.selectById(caseId);
//        if (casePojo == null){
//            throw new BusinessException("该病例不存在");
//        }
//        return casePojo;
//    }

    //新增病例,返回病例号
    public void addCase(CasePojo casePojo){
//        casePojo.setCaseNumber(snowFlake.nextId());
        try {
            caseDao.insert(casePojo);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
//        log.info("caseNumber:{}",casePojo.getCaseNumber());
    }

    //根据病例号删除病例
    public void delCase(Long caseNumber){
//        if (caseDao.delete(new QueryWrapper<CasePojo>().eq("case_number", caseNumber)) == 0){
        if (caseDao.deleteById(caseNumber) == 0){
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

    //跟新病例的病患信息
    public void updateCase(CasePojo newCase){
//        if (caseDao.updateById(newCase) == 0){
//            throw new BusinessException("病例不存在，修改失败");
//        }
        UpdateWrapper<CasePojo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("case_number",newCase.getCaseNumber())
                .set("address_id",newCase.getAddressId())
                .set("birthday",newCase.getBirthday())
                .set("gender",newCase.getGender())
                .set("patient_name",newCase.getPatientName())
                .set("doctor_id",newCase.getDoctorId())
                .set("patient_complaint",newCase.getPatientComplaint());
        if (caseDao.update(null,updateWrapper) == 0){
            throw new BusinessException("病例不存在");
        }
    }

    /*
    * 获取病例的临床信息
    * */
    public CasePojo getClinicalCircumstance(Long caseNumber){
        CasePojo casePojo = caseDao.selectById(caseNumber);
        if (casePojo == null){
            throw new BusinessException("病例不存在");
        }
        return casePojo;
    }

    /*
    * 更新病例的临床信息
    * */
    public void updateClinicalCircumstance(Long caseNumber,String diagnosisInfos,String doctorRemark){
        UpdateWrapper<CasePojo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("case_number",caseNumber)
                .set("diagnosis_infos",diagnosisInfos)
                .set("doctor_remark",doctorRemark);
        if (caseDao.update(null,updateWrapper) == 0){
            throw new BusinessException("病例不存在");
        }
    }

    /*
    * 根据病例号获取排牙设定
    * */
    public CasePojo getToothSetByNumber(Long caseNumber){
        CasePojo casePojo = caseDao.selectById(caseNumber);
        if (casePojo == null){
            throw new BusinessException("病例不存在");
        }
        return casePojo;
    }

    /*
    * 改变病例状态
    * */
    public void updateCaseState(Long caseNumber,int caseState){
        if (caseDao.update(null,new UpdateWrapper<CasePojo>().eq("case_number",caseNumber)
                .set("case_state",caseState)) == 0){
            throw new BusinessException("病例不存在");
        }
    }

    /*
    * 为病例分配技工
    * */
    public void updateMechanic(Long caseNumber,Integer mechanicId){
        if (caseDao.update(null,new UpdateWrapper<CasePojo>().eq("case_number",caseNumber)
                .set("mechanic_id",mechanicId)) == 0){
            throw new BusinessException("病例不存在");
        }
    }


    /*
    * 获取病例是否能够修改
    * */
    public Boolean getLimitByNumber(Long caseNumber){
        CasePojo casePojo = caseDao.selectById(caseNumber);
        if (casePojo == null){
            throw new BusinessException("病例不存在");
        }
        if (casePojo.getCaseState() <= 2){
            return true;
        } else {
            return false;
        }
    }

    public void updateLowerSentStep(Long caseNumber,Integer stepsLowOver){
        if (caseDao.update(null,new UpdateWrapper<CasePojo>().eq("case_number",caseNumber)
                .set("lower_sent_step",stepsLowOver)) == 0){
            throw new BusinessException("病例不存在");
        }
    }

    public void updateUpperSentStep(Long caseNumber,Integer stepsUpOver){
        if (caseDao.update(null,new UpdateWrapper<CasePojo>().eq("case_number",caseNumber)
                .set("lower_sent_step",stepsUpOver)) == 0){
            throw new BusinessException("病例不存在");
        }
    }
}
