package com.hzy.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.Date;

/**
 * 病人监护人信息表
 * @TableName caregiver_info
 */
@TableName(value = "caregiver_info")
@Data
@Schema(description = "病人监护人信息")
public class CaregiverInfo extends BaseEntity {



    @Schema(description = "关联的系统用户ID，可为空（无账号的监护人）")
    @TableField(value = "user_id")
    private Long userId;

    @Schema(description = "监护人姓名")
    @TableField(value = "name")
    private String caregiverName;

    @Schema(description = "性别：1-男 2-女 0-未知")
    @TableField(value = "gender")
    private Integer gender;

    @Schema(description = "出生日期")
    @TableField(value = "birth_date")
    private Date birthDate;

    @Schema(description = "身份证号")
    @TableField(value = "id_card")
    private String idCard;

    @Schema(description = "与病人关系：1-父母 2-子女 3-配偶 4-亲属 5-其他")
    @TableField(value = "relationship")
    private Integer relationship;

    @Schema(description = "监护人联系电话，用于事件通知")
    @TableField(value = "phone")
    private String phone;

    @Schema(description = "监护人邮箱")
    @TableField(value = "email")
    private String email;

    @Schema(description = "家庭住址")
    @TableField(value = "home_address")
    private String homeAddress;

    @Schema(description = "事件通知等级阈值：1-全部通知 2-仅中高风险 3-仅高风险")
    @TableField(value = "notify_level")
    private Integer notifyLevel;

    @Schema(description = "备注信息")
    @TableField(value = "remark")
    private String remark;
}