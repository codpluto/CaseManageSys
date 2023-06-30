package com.zhu.casemanage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.jdbc.Null;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
public class UserPojo {
    @TableId(value = "user_id",type = IdType.AUTO)
    private int userId;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "password")
    private String password;

    @TableField(value = "user_type")
    private int userType;

    @TableField(value = "user_face")
    private String userFace;

    @TableField(value = "user_phone")
    private String userPhone;

    @TableField(value = "user_email")
    private String userEmail;

    @TableField(value = "account")
    private String account;

    @TableField(value = "org_id")
    private int orgId;

}
