package com.zhu.casemanage.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhu.casemanage.dao.CaseDao;
import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.pojo.CasePojo;
import com.zhu.casemanage.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        casePojo.setFacePhoto(Constant.FACEPHOTO);
//        casePojo.setIsDeleted(false);
        caseDao.insert(casePojo);
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
     * 更新病例的上颌已生产步数
     * */
    public void updateLowerSentStep(Long caseNumber, int stepsLowOver) {
        if (caseDao.update(null,new UpdateWrapper<CasePojo>().eq("case_number",caseNumber)
                .set("lower_sent_step",stepsLowOver)) == 0){
            throw new BusinessException("病例不存在");
        }
    }

    /*
     * 更新病例的下颌已生产步数
     * */
    public void updateUpperSentStep(Long caseNumber, int stepsLowOver) {
        if (caseDao.update(null,new UpdateWrapper<CasePojo>().eq("case_number",caseNumber)
                .set("upper_sent_step",stepsLowOver)) == 0){
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
    public void updateMechanic(Long casNumber,Integer mechanicId){
        if (caseDao.update(null,new UpdateWrapper<CasePojo>().eq("case_number",casNumber)
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

    /*
    * 返回筛选后病例的第pageNum页
    * */
    public Map<String,Object> showCaseInfoPage(int pageNum,int pageSize,int status,String param){
        Page<CasePojo> casePojoPage = new Page<>(pageNum,pageSize);
        QueryWrapper<CasePojo> wrapper = new QueryWrapper<>();
        if (status != -1){
            wrapper.eq("case_state",status);
        }
        if (!param.isBlank()){
            wrapper.and(Wrapper -> Wrapper.like("patient_name",param).or().like("case_number",param).or().like("clinic",param));
        }
        IPage<CasePojo> caseIPage = caseDao.selectPage(casePojoPage,wrapper);
        List<CasePojo> records = caseIPage.getRecords();
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",records);
        map.put("endRow",records.size());
        map.put("firstPage",1);
        map.put("lastPage",pageSize);
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        map.put("pages",caseIPage.getPages());
        map.put("prePage",pageNum-1);
        map.put("total",caseIPage.getTotal());
        if (pageNum == 1){
            map.put("isFirstPage",true);
        } else {
            map.put("isFirstPage",false);
        }
        if (pageNum == caseIPage.getPages()){
            map.put("isLastPage",true);
            map.put("hasNextPage",false);
            map.put("nextPage",0);
        } else {
            map.put("isLastPage",false);
            map.put("hasNextPage",true);
            map.put("nextPage",pageNum+1);
        }
        return map;
    }

    /*
     * 返回按用户类型筛选后的待处理病例的第pageNum页
     * */
    public Map<String,Object> showCaseInfoPageByUserType(int pageNum,int pageSize,int userType,int status,String param){
        Page<CasePojo> casePojoPage = new Page<>(pageNum,pageSize);
        QueryWrapper<CasePojo> wrapper = new QueryWrapper<>();
        switch (userType){
            case 1 -> wrapper.and(Wrapper -> Wrapper.eq("case_state",1)
                    .or().eq("case_state",2)
                    .or().eq("case_state",8)
                    .or().eq("case_state",11)
                    .or().eq("case_state",12)
                    .or().eq("case_state",14));
            case 2 -> wrapper.and(Wrapper -> Wrapper.eq("case_state",7));
            case 3 -> wrapper.and(Wrapper -> Wrapper.eq("case_state",3)
                    .or().eq("case_state",4)
                    .or().eq("case_state",5)
                    .or().eq("case_state",6)
                    .or().eq("case_state",9)
                    .or().eq("case_state",10));
            case 4 -> wrapper.and(Wrapper -> Wrapper.eq("case_state",6));
        }
        if (status != -1){
            wrapper.and(Wrapper -> Wrapper.eq("case_state",status));
        }
        if (!param.isBlank()){
            wrapper.and(Wrapper -> Wrapper.like("patient_name",param).or().like("case_number",param).or().like("clinic",param));
        }
        IPage<CasePojo> caseIPage = caseDao.selectPage(casePojoPage,wrapper);
        List<CasePojo> records = caseIPage.getRecords();
        HashMap<String, Object> map = new HashMap<>();
        map.put("list",records);
        map.put("endRow",records.size());
        map.put("firstPage",1);
        map.put("lastPage",pageSize);
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        map.put("pages",caseIPage.getPages());
        map.put("prePage",pageNum-1);
        map.put("total",caseIPage.getTotal());
        if (pageNum == 1){
            map.put("isFirstPage",true);
        } else {
            map.put("isFirstPage",false);
        }
        if (pageNum == caseIPage.getPages()){
            map.put("isLastPage",true);
            map.put("hasNextPage",false);
            map.put("nextPage",0);
        } else {
            map.put("isLastPage",false);
            map.put("hasNextPage",true);
            map.put("nextPage",pageNum+1);
        }
        return map;
    }


    /*
    * 更新病例的头像
    * */
    public void updateCaseFacePhoto(Long caseNumber,String facePhoto){
        UpdateWrapper<CasePojo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("case_number",caseNumber).set("face_photo",facePhoto);
        caseDao.update(null,updateWrapper);
    }






    /*
     * 返回按关键字搜索(selectParam)的全部病例的第pageNum页
     * */
//    public Map<String,Object> showCaseInfoPageByParam(int pageNum,int pageSize,String param){
//        Page<CasePojo> casePojoPage = new Page<>(pageNum,pageSize);
//        IPage<CasePojo> caseIPage = caseDao.selectPage(casePojoPage,new QueryWrapper<CasePojo>().like("patient_name",param)
//                .or().like("case_number",param)
//                .or().like("clinic",param));
//        List<CasePojo> records = caseIPage.getRecords();
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("list",records);
//        map.put("endRow",records.size());
//        map.put("firstPage",1);
//        map.put("lastPage",pageSize);
//        map.put("pageNum",pageNum);
//        map.put("pageSize",pageSize);
//        map.put("pages",caseIPage.getPages());
//        map.put("prePage",pageNum-1);
//        map.put("total",caseIPage.getTotal());
//        if (pageNum == 1){
//            map.put("isFirstPage",true);
//        } else {
//            map.put("isFirstPage",false);
//        }
//        if (pageNum == caseIPage.getPages()){
//            map.put("isLastPage",true);
//            map.put("hasNextPage",false);
//            map.put("nextPage",0);
//        } else {
//            map.put("isLastPage",false);
//            map.put("hasNextPage",true);
//            map.put("nextPage",pageNum+1);
//        }
//        return map;
//    }

}
