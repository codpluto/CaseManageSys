package com.zhu.casemanage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "send")
public class SendPojo {
    @TableId(value = "id",type = IdType.AUTO)
    private int sendId;

    @TableField(value = "case_number")
    private Long caseNumber;

    @TableField(value = "create_time")
    private Timestamp createTime;

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
