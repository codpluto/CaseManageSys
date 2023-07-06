package com.zhu.casemanage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "3dscheme")
public class TDSchemePojo {
    @TableId(value = "id",type = IdType.AUTO)
    private int tDschemeId;

    @TableField(value = "scheme_name")
    private String schemeName;

    @TableField(value = "is_pass")
    private boolean isPass;

    @TableField(value = "is_to_view")
    private boolean isToViem;

    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(value = "case_number")
    private Long caseNumber;

    @TableField(value = "scheme_url")
    private String schemeUrl;


}
