package com.zhu.casemanage.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zhu.casemanage.dao.SchemeDao;
import com.zhu.casemanage.pojo.SchemePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchemeServiceImpl {
    @Autowired
    private SchemeDao schemeDao;

    /*
    * 新增治疗方案
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
        updateWrapper.eq("case_number",toothSet.getCaseNumber());
        updateWrapper.set("orthodontic_arch",toothSet.getOrthodonticArch());
        updateWrapper.set("ext_tooth",toothSet.getExtTooth());
        updateWrapper.set("tooth_extraction",toothSet.getToothExtraction());
        updateWrapper.set("anchorage_left",toothSet.getAnchorageLeft());
        updateWrapper.set("anchorage_right",toothSet.getAnchorageRight());
        updateWrapper.set("centerline_choice_up",toothSet.getCenterlineChoiceUp());
        updateWrapper.set("centerline_choice_low",toothSet.getCenterlineChoiceLow());
        updateWrapper.set("centerline_up_val",toothSet.getCenterlineUpVal());
        updateWrapper.set("centerline_low_val",toothSet.getCenterlineLowVal());
        updateWrapper.set("overbite_adjust",toothSet.getOverbiteAdjust());
        updateWrapper.set("overjet_adjust",toothSet.getOverjetAdjust());
        updateWrapper.set("overjet_val",toothSet.getOverjetVal());
        updateWrapper.set("molar_relationship_left",toothSet.getMolarRelationshipLeft());
        updateWrapper.set("molar_relationship_right",toothSet.getMolarRelationshipRight());
        updateWrapper.set("crowding_tooth_up",toothSet.getCrowdingToothUp());
        updateWrapper.set("crowding_tooth_low",toothSet.getCrowdingToothLow());
        updateWrapper.set("crowding_tooth_up_val",toothSet.getCrowdingToothUpVal());
        updateWrapper.set("crowding_tooth_low_val",toothSet.getCrowdingToothLowVal());
        updateWrapper.set("move",toothSet.getMove());
        updateWrapper.set("angle",toothSet.getAngle());
        updateWrapper.set("preinstall",toothSet.getPreinstall());
        schemeDao.update(null,updateWrapper);
    }
}
