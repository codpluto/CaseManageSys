package com.zhu.casemanage.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "case_tracking")
public class TrackPojo {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer trackId;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "status")
    private int status;

    @TableField(value = "remark")
    private String remark;

    @TableField(value = "remark_en")
    private String remarkEn;

    @TableField(value = "case_number")
    private Long caseNumber;

}
