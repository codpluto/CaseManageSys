package com.zhu.casemanage.pojo;

import com.baomidou.mybatisplus.annotation.*;
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
    private LocalDateTime createTime;

    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "file_net_url")
    private String fileNetUrl;

    @TableField(value = "scheme_id")
    private int schemeId;
}
