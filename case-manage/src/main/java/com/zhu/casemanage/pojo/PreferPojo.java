package com.zhu.casemanage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "preference")
public class PreferPojo {
    @TableId(value = "preference_id",type = IdType.AUTO)
    private int preferenceId;

    @TableField(value = "preference_name")
    private String preferenceName;

    @TableField(value = "orthodontic_arch")
    private int orthodonticArch;

    @TableField(value = "tooth_extraction")
    private String toothExtraction;

    @TableField(value = "anchorage_left")
    private int anchorageLeft;

    @TableField(value = "anchorage_right")
    private int anchorageRight;

    @TableField(value = "centerline_choice_up")
    private int centerlineChoiceUp;

    @TableField(value = "centerline_choice_low")
    private int centerlineChoiceLow;

    @TableField(value = "centerline_up_val")
    private float centerlineUpVal;

    @TableField(value = "centerline_low_val")
    private float centerlineLowVal;

    @TableField(value = "overbite_adjust")
    private int overbiteAdjust;

    @TableField(value = "cover_adjust")
    private int coverAdjust;

    @TableField(value = "cover_val")
    private float coverVal;

    @TableField(value = "molar_relationship_left")
    private int molarRelationshipLeft;

    @TableField(value = "molar_relationship_right")
    private int molarRelationshipRight;

    @TableField(value = "crowding_tooth_up")
    private String crowdingToothUp;

    @TableField(value = "crowding_tooth_low")
    private String crowdingToothLow;

    @TableField(value = "crowding_tooth_up_val")
    private float crowdingToothUpVal;

    @TableField(value = "crowding_tooth_low_val")
    private float crowdingToothLowVal;

    @TableField(value = "move")
    private int move;

    @TableField(value = "angle")
    private int angle;

    @TableField(value = "preinstall")
    private String preinstall;

    @TableField(value = "unmovable_teeth")
    private String unmovableTeeth;

    @TableField(value = "accessory_teeth")
    private String accessoryTeeth;

    @TableField(value = "interval_teeth")
    private String intervalTeeth;

    @TableField(value = "adjacent_glaze")
    private String adjacentGlaze;

    @TableField(value = "is_excessive")
    private int isExcessive;

    @TableField(value = "demand")
    private String demand;

    @TableField(value = "user_id")
    private int userId;

}
