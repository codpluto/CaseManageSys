package com.zhu.casemanage.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "address")
public class AddressPojo {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer addressId;

    @TableField(value = "clinic")
    private String clinic;

    @TableField(value = "consignee")
    private String consignee;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "consignee_address")
    private String consigAddress;

    @TableField(value = "postcode")
    private String postcode;

    @TableField(value = "user_id")
    private int userId;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}
