package com.hzy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@TableName(value ="patient_info")
@Data
@Schema(description = "病人基础信息")
public class PatientInfo extends BaseEntity {

    @TableId(value = "patient_id", type = IdType.AUTO)
    @Schema(description = "病人ID")
    private Long id;

    @Schema(description = "病人姓名")
    @TableField(value = "patient_name")
    private String patientName;

    @Schema(description = "性别")
    @TableField(value = "gender")
    private Integer gender;

    @Schema(description = "出生日期")
    @TableField(value = "birth_date")
    private Date birthDate;

    @Schema(description = "身份证号")
    @TableField(value = "id_card")
    private String idCard;

    @Schema(description = "本人联系电话")
    @TableField(value = "phone")
    private String phone;

    @Schema(description = "紧急联系人电话")
    @TableField(value = "emergency_contact_phone")
    private String emergencyContactPhone;

    @Schema(description = "家庭住址")
    @TableField(value = "home_address")
    private String homeAddress;

    @Schema(description = "所在房间ID")
    @TableField(value = "room_id")
    private Long roomId;

    @Schema(description = "绑定设备ID")
    @TableField(value = "equipment_id")
    private Long equipmentId;

    @Schema(description = "默认工作人员ID")
    @TableField(value = "staff_id")
    private Long staffId;

    @Schema(description = "设备监测状态")
    @TableField(value = "equipment_status")
    private Integer equipmentStatus;

    @Schema(description = "病人健康状态")
    @TableField(value = "patient_status")
    private Integer patientStatus;

    @Schema(description = "风险等级")
    @TableField(value = "event_grade")
    private Integer eventGrade;

    @Schema(description = "备注信息")
    @TableField(value = "remark")
    private String remark;
}