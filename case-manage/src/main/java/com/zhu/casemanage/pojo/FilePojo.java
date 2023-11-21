package com.zhu.casemanage.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "file")
public class FilePojo {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer fileId;

    @TableField(value = "file_name")
    private String fileName;

    @TableField(value = "file_url")
    private String fileUrl;

    @TableField(value = "file_type")
    private int fileType;

    @TableField(value = "case_number")
    private Long caseNumber;

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone="GMT+8")
    private LocalDateTime createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone="GMT+8")
    private LocalDateTime updateTime;

    @TableField(value = "file_net_url")
    private String fileNetUrl;

    @TableField(value = "scheme_id")
    private int schemeId;

    @TableField(value = "is_pass")
    private int isPass;

    @TableField(value = "remark")
    private String remark;

    @TableField(value = "has_examined")
    private int hasExamined;
}
