package com.zhu.casemanage.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "file")
public class FilePojo {
    @TableId(value = "file_id",type = IdType.AUTO)
    private int fileId;

    @TableField(value = "file_name")
    private String fileName;

    @TableField(value = "file_url")
    private String fileUrl;

    @TableField(value = "file_type")
    private int fileType;

    @TableField(value = "case_number")
    private int caseNumber;

    @TableField(value = "update_time")
    private Timestamp updateTime;

    @TableField(value = "file_net_url")
    private String fileNetUrl;

    @TableField(value = "scheme_id")
    private int schemeId;
}
