package com.zhu.casemanage.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "send")
public class SendPojo {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer sendId;

    @TableField(value = "case_number")
    private Long caseNumber;

    @TableField(value = "express_type")
    private int expressType;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone="GMT+8")
    private LocalDateTime createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone="GMT+8")
    private LocalDateTime updateTime;

    @TableField(value = "express_id")
    private int expressId;

    @TableField(value = "express_num")
    private String expressNum;

    @TableField(value = "lower_num")
    private int lowerNum;

    @TableField(value = "steps_low_start")
    private int stepsLowStart;

    @TableField(value = "steps_low_over")
    private int stepsLowOver;

    @TableField(value = "upper_num")
    private int upperNum;

    @TableField(value = "steps_up_start")
    private int stepsUpStart;

    @TableField(value = "steps_up_over")
    private int stepsUpOver;

    @TableField(value = "total_num")
    private int totalNum;

}
