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
@TableName(value = "case_info")
public class CasePojo {
//    @TableId(value = "id",type = IdType.AUTO)
//    private int caseId;

    @TableId(value = "case_number",type = IdType.ASSIGN_ID)
    private Long caseNumber;

    @TableField(value = "patient_name")
    private String patientName;

    @TableField(value = "doctor_name")
    private String doctorName;

    @TableField(value = "gender")
    private int gender;

    @TableField(value = "clinic")
    private String clinic;

    @TableField(value = "birthday")
    private Date birthday;

    @TableField(value = "address")
    private String address;

    @TableField(value = "patient_complaint")
    private String patientComplaint;

    @TableField(value = "age")
    private int age;

    @TableField(value = "profession")
    private String profession;

    @TableField(value = "patient_phone")
    private String patientPhone;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "commit_time")
    private Date commitTime;

    @TableField(value = "case_state")
    private int caseState;

    @TableField(value = "restart_case_number")
    private Long restartCaseNumber;

    @TableField(value = "diagnosis_infos")
    private String diagnosisInfos;

    @TableField(value = "doctor_remark")
    private String doctorRemark;

    @TableField(value = "face_photo")
    private String facePhoto;

    @TableField(value = "address_id")
    private int addressId;

    @TableField(value = "init_case_number")
    private Long initCaseNumber;


    @TableField(value = "is_valid")
    private Boolean isValid;

    @TableField(value = "mechanic_id")
    private int mechanicId;

    @TableField(value = "doctor_id")
    private int doctorId;

    @TableField(value = "treatment_date")
    private Date treatmentDate;

    @TableField(value = "lower_sent_step")
    private int lowerSentStep;

    @TableField(value = "wear_remain")
    private int wearRemain;

    @TableField(value = "lower_total_step")
    private int lowerTotalStep;

    @TableField(value = "upper_sent_step")
    private int upperSentStep;

    @TableField(value = "upper_total_step")
    private int upperTotalStep;

    @TableField(value = "wear_step")
    private int wearStep;


}
