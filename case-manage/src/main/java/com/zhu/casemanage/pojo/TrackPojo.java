package com.zhu.casemanage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "case_tracking")
public class TrackPojo {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer trackId;

    @TableField(value = "create_time")
    private Timestamp createTime;

    @TableField(value = "status")
    private int status;

    @TableField(value = "remark")
    private String remark;

    @TableField(value = "remark_en")
    private String remarkEn;

    @TableField(value = "case_number")
    private Long caseNumber;

}
