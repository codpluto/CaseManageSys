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
@TableName(value = "address")
public class AddressPojo {
    @TableId(value = "id",type = IdType.AUTO)
    private int addressId;

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
}
