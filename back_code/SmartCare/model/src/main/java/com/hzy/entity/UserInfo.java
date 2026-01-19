package com.hzy.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.Data;

/**
 * 系统用户账号表
 * @TableName user_info
 */
@TableName(value ="user_info")
@Data
@Schema(description = "系统用户账号")
public class UserInfo extends BaseEntity {



    @Schema(description = "登录用户名")
    @TableField(value = "username")
    private String username;

    @Schema(description = "登录密码哈希值")
    @TableField(value = "password_hash",select = false)
    private String passwordHash;

    @Schema(description = "用户类型：1-患者 2-家属 3-护工 4-医护人员 9-系统管理员")
    @TableField(value = "type")
    private Integer userType;

    @Schema(description = "默认关联的病人ID")
    @TableField(value = "patient_id")
    private Long patientId;

    @Schema(description = "手机号")
    @TableField(value = "phone")
    private String phone;

    @Schema(description = "邮箱")
    @TableField(value = "email")
    private String email;

    @Schema(description = "账号状态：1-正常 2-禁用 3-冻结")
    @TableField(value = "status")
    private Integer status;

    @Schema(description = "最近一次登录时间")
    @TableField(value = "last_login_time")
    private Date lastLoginTime;

    @Schema(description = "最近一次登录IP")
    @TableField(value = "last_login_ip")
    private String lastLoginIp;
}