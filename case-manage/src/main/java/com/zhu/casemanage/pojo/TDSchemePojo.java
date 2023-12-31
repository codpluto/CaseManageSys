package com.zhu.casemanage.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "3dscheme")
public class TDSchemePojo {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer tdSchemeId;

    @TableField(value = "scheme_name")
    private String schemeName;

    @TableField(value = "is_pass")
    private int isPass;

    @TableField(value = "is_viewed")
    private int isViewed;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone="GMT+8")
    private LocalDateTime createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone="GMT+8")
    private LocalDateTime updateTime;

    @TableField(value = "case_number")
    private Long caseNumber;

    @TableField(value = "scheme_url")
    private String schemeUrl;

    @TableField(value = "remark")
    private String remark;

    @TableField(value = "auditor_type")
    private int auditorType;

    @TableField(value = "auditor_id")
    private int auditorId;

    @TableField(value = "lower_sent_step")
    private int lowerSentStep;

    @TableField(value = "lower_total_step")
    private int lowerTotalStep;

    @TableField(value = "upper_sent_step")
    private int upperSentStep;

    @TableField(value = "upper_total_step")
    private int upperTotalStep;

    @TableField(value = "wear_step")
    private int wearStep;

    @TableField(value = "wear_remain")
    private int wearRemain;

}
