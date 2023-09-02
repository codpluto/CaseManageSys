package com.zhu.casemanage.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "preference")
public class PreferPojo {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer preferId;

    @TableField(value = "preinstall")
    private String preinstall;

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

    @TableField(value = "overjet_adjust")
    private int overjetAdjust;

    @TableField(value = "overjet_val")
    private float overjetVal;

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

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone="GMT+8")
    private LocalDateTime createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone="GMT+8")
    private LocalDateTime updateTime;

}
