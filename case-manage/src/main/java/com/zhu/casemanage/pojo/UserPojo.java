package com.zhu.casemanage.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.jdbc.Null;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
public class UserPojo {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer userId;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "password")
    private String password;

    @TableField(value = "user_type")
    private int userType;

    @TableField(value = "face_photo")
    private String facePhoto;

    @TableField(value = "user_phone")
    private String userPhone;

    @TableField(value = "user_email")
    private String userEmail;

    @TableField(value = "account")
    private String account;


    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableLogic
    @TableField(value = "is_deleted")
    private Boolean isDeleted;
}
