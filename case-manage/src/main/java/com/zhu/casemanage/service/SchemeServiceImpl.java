package com.zhu.casemanage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zhu.casemanage.dao.SchemeDao;
import com.zhu.casemanage.dao.TDSchemeDao;
import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.pojo.SchemePojo;
import com.zhu.casemanage.pojo.TDSchemePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchemeServiceImpl {
    @Autowired
    private SchemeDao schemeDao;
    @Autowired
    private TDSchemeDao tdSchemeDao;

    /*
    * 新增治疗方案/新增排牙设定
    * */
    public void addScheme(SchemePojo newScheme){
        schemeDao.insert(newScheme);
    }

    /*
    * 根据病例号查询治疗方案
    * */
    public SchemePojo getSchemeByNumber(Long caseNumber){
        return schemeDao.selectOne(new QueryWrapper<SchemePojo>().eq("case_number", caseNumber));
    }



    /*
    * 更新排牙设定
    * */
    public void updateToothSet(SchemePojo toothSet){
        UpdateWrapper<SchemePojo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("case_number",toothSet.getCaseNumber())
                .set("orthodontic_arch",toothSet.getOrthodonticArch())
                .set("ext_tooth",toothSet.getExtTooth())
                .set("tooth_extraction",toothSet.getToothExtraction())
                .set("anchorage_left",toothSet.getAnchorageLeft())
                .set("anchorage_right",toothSet.getAnchorageRight())
                .set("centerline_choice_up",toothSet.getCenterlineChoiceUp())
                .set("centerline_choice_low",toothSet.getCenterlineChoiceLow())
                .set("centerline_up_val",toothSet.getCenterlineUpVal())
                .set("centerline_low_val",toothSet.getCenterlineLowVal())
                .set("overbite_adjust",toothSet.getOverbiteAdjust())
                .set("overjet_adjust",toothSet.getOverjetAdjust())
                .set("overjet_val",toothSet.getOverjetVal())
                .set("molar_relationship_left",toothSet.getMolarRelationshipLeft())
                .set("molar_relationship_right",toothSet.getMolarRelationshipRight())
                .set("crowding_tooth_up",toothSet.getCrowdingToothUp())
                .set("crowding_tooth_low",toothSet.getCrowdingToothLow())
                .set("crowding_tooth_up_val",toothSet.getCrowdingToothUpVal())
                .set("crowding_tooth_low_val",toothSet.getCrowdingToothLowVal())
                .set("move",toothSet.getMove())
                .set("angle",toothSet.getAngle())
                .set("preinstall",toothSet.getPreinstall());
        if (schemeDao.update(null,updateWrapper) == 0){
            throw new BusinessException("病例不存在");
        }
    }

    /*
    * 更新牙位标记
    * */
    public void updateToothTag(SchemePojo toothTag){
        UpdateWrapper<SchemePojo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("case_number",toothTag.getCaseNumber())
                .set("accessory_teeth",toothTag.getAccessoryTeeth())
                .set("adjacent_glaze",toothTag.getAdjacentGlaze())
                .set("demand",toothTag.getDemand())
                .set("interval_teeth",toothTag.getIntervalTeeth())
                .set("is_excessive",toothTag.getIsExcessive())
                .set("unmovable_teeth",toothTag.getUnmovableTeeth());
        if (schemeDao.update(null,updateWrapper) == 0){
            throw new BusinessException("病例不存在");
        }
    }

    /*
    * 新增3d方案
    * */
    public void add3dScheme(TDSchemePojo tdSchemePojo){
        tdSchemeDao.insert(tdSchemePojo);
    }

    /*
    * 查询某病例最新的3d方案（还未被审核的那个）
    * */
    public TDSchemePojo getLastTDScheme(Long caseNumber){
        TDSchemePojo tdSchemeLast = tdSchemeDao.selectOne(new QueryWrapper<TDSchemePojo>().eq("case_number", caseNumber).eq("is_to_view", 0));
        if (tdSchemeLast == null){
            throw new BusinessException("3d治疗方案不存在");
        }
        return tdSchemeLast;
    }

    /*
    * 审核3d方案（修改是否通过和是否审核）
    * */
    public void update3dScheme(TDSchemePojo tdSchemePojo){
        UpdateWrapper<TDSchemePojo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",tdSchemePojo.getTdSchemeId())
                .set("is_viewed",1).set("is_pass",tdSchemePojo.getIsPass()).set("remark",tdSchemePojo.getRemark())
                .set("auditor_id",tdSchemePojo.getAuditorId());
        if (tdSchemeDao.update(null,updateWrapper) == 0){
            throw new BusinessException("3d治疗方案不存在");
        }
    }

    /*
    * 根据病例号返回3d方案列表
    * */
    public List<TDSchemePojo> get3dSchemeList(Long caseNumber){
        List<TDSchemePojo> schemePojoList = tdSchemeDao.selectList(new QueryWrapper<TDSchemePojo>().eq("case_number", caseNumber));
        if (schemePojoList.size() == 0){
            throw new BusinessException("未上传3d方案");
        }
        return schemePojoList;
    }
}
