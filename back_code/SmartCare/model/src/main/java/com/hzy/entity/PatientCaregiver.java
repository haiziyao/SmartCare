package com.hzy.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzy.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@TableName(value ="patient_caregiver")
@Data
@Schema(description = "病人与监护人关系")
public class PatientCaregiver extends BaseEntity {



    @Schema(description = "监护人ID")
    @TableField(value = "caregiver_id")
    private Long caregiverId;

    @Schema(description = "是否主监护人")
    @TableField(value = "is_primary")
    private Integer isPrimary;

    @Schema(description = "是否启用事件通知")
    @TableField(value = "notify_enabled")
    private Integer notifyEnabled;

    @Schema(description = "关系状态：1-有效 2-暂停 3-已解除")
    @TableField(value = "relation_status")
    private Integer relationStatus;

    @Schema(description = "关系生效时间")
    @TableField(value = "start_time")
    private Date startTime;

    @Schema(description = "关系失效时间")
    @TableField(value = "end_time")
    private Date endTime;

    @Schema(description = "备注信息")
    @TableField(value = "remark")
    private String remark;
}