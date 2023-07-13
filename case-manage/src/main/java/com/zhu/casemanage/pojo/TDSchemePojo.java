package com.zhu.casemanage.pojo;

import com.baomidou.mybatisplus.annotation.*;
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
    private Integer tDschemeId;

    @TableField(value = "scheme_name")
    private String schemeName;

    @TableField(value = "is_pass")
    private Boolean isPass;

    @TableField(value = "is_viewed")
    private Boolean isViewed;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
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


}
