package com.zhu.casemanage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "send")
public class SendPojo {
    @TableId(value = "send_id",type = IdType.AUTO)
    private int sendId;

    @TableField(value = "send_step_lower")
    private int sendStepLower;

    @TableField(value = "send_step_upper")
    private int sendStepUpper;

    @TableField(value = "send_list")
    private String sendList;

    @TableField(value = "wear_remain")
    private int wearRemain;

    @TableField(value = "total_step_lower")
    private int totalStepLower;

    @TableField(value = "total_step_upper")
    private int totalStepUpper;

    @TableField(value = "send_list_new")
    private String sendListNew;

    @TableField(value = "wear_step")
    private int wearStep;

    @TableField(value = "case_id")
    private int caseId;
}
