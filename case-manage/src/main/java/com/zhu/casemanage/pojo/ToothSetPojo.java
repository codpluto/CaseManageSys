//package com.zhu.casemanage.pojo;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@TableName(value = "tooth_set")
//public class ToothSetPojo {
//    @TableId(value = "tooth_set_id",type = IdType.AUTO)
//    private int toothSetId;
//
//    @TableField(value = "case_id")
//    private int caseId;
//
//    @TableField(value = "orthodontic_arch")
//    private int orthodonticArch;
//
//    @TableField(value = "tooth_extraction")
//    private String toothExtraction;
//
//    @TableField(value = "anchorage_left")
//    private int anchorageLeft;
//
//    @TableField(value = "anchorage_right")
//    private int anchorageRight;
//
//    @TableField(value = "centerline_choice_low")
//    private int centerlineChoiceLow;
//
//    @TableField(value = "centerline_choice_up")
//    private int centerlineChoiceUp;
//
//    @TableField(value = "centerline_low_val")
//    private float centerlineLowVal;
//
//    @TableField(value = "centerline_up_val")
//    private float centerlineUpVal;
//
//    @TableField(value = "ext_tooth")
//    private int extTooth;
//
//    @TableField(value = "cover_adjust")
//    private int coverAdjust;
//
//    @TableField(value = "cover_val")
//    private float coverVal;
//
//    @TableField(value = "molar_relationship_left")
//    private int molarRelationshipLeft;
//
//    @TableField(value = "molar_relationship_right")
//    private int molarRelationshipRight;
//
//    @TableField(value = "crowding_teeth_low")
//    private String crowdingTeethLow;
//
//    @TableField(value = "crowding_teeth_low_val")
//    private float crowdingTeethLowVal;
//
//    @TableField(value = "crowding_teeth_up")
//    private String crowdingTeethUp;
//
//    @TableField(value = "crowding_teeth_up_val")
//    private float crowdingTeethUpVal;
//
//    @TableField(value = "move")
//    private int move;
//
//    @TableField(value = "angle")
//    private int angle;
//
//    @TableField(value = "overbite_adjust")
//    private String overbiteAdjust;
//
//    @TableField(value = "pre_install")
//    private String preInstall;
//}
