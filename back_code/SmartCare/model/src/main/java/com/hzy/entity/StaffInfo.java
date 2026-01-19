package com.hzy.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 工作人员信息表
 * @TableName staff_info
 */
@TableName(value ="staff_info")
@Data
@Schema(description = "工作人员信息")
public class StaffInfo extends BaseEntity {



    @Schema(description = "关联的系统用户ID")
    @TableField(value = "user_id")
    private Long userId;

    @Schema(description = "工作人员姓名")
    @TableField(value = "staff_name")
    private String staffName;

    @Schema(description = "工作人员角色：1-护工 2-护士 3-医生 4-运维 9-管理员")
    @TableField(value = "staff_role")
    private Integer staffRole;

    @Schema(description = "工作人员联系电话")
    @TableField(value = "phone")
    private String phone;

    @Schema(description = "工作人员邮箱")
    @TableField(value = "email")
    private String email;

    @Schema(description = "在岗状态：1-在岗 2-离岗 3-休假")
    @TableField(value = "work_status")
    private Integer workStatus;

    @Schema(description = "备注信息")
    @TableField(value = "remark")
    private String remark;
}