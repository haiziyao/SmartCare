package com.hzy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@TableName(value ="param_info")
@Data
@Schema(description = "生理参数原始统计结果")
public class ParamInfo extends BaseEntity {

    @Schema(description = "病人ID")
    @TableField(value = "patient_id")
    private Long patientId;

    @Schema(description = "设备ID")
    @TableField(value = "equipment_id")
    private Long equipmentId;

    @Schema(description = "统计窗口开始时间")
    @TableField(value = "window_start_time")
    private Date windowStartTime;

    @Schema(description = "统计窗口结束时间")
    @TableField(value = "window_end_time")
    private Date windowEndTime;

    @Schema(description = "心率平均值")
    @TableField(value = "hr_avg")
    private Integer hrAvg;

    @Schema(description = "心率最大值")
    @TableField(value = "hr_max")
    private Integer hrMax;

    @Schema(description = "心率最小值")
    @TableField(value = "hr_min")
    private Integer hrMin;

    @Schema(description = "心率百分偏差")
    @TableField(value = "hr_diff")
    private BigDecimal hrDiff;

    @Schema(description = "呼吸率平均值")
    @TableField(value = "rr_avg")
    private Integer rrAvg;

    @Schema(description = "呼吸率最大值")
    @TableField(value = "rr_max")
    private Integer rrMax;

    @Schema(description = "呼吸率最小值")
    @TableField(value = "rr_min")
    private Integer rrMin;

    @Schema(description = "呼吸率百分偏差")
    @TableField(value = "rr_diff")
    private BigDecimal rrDiff;

    @Schema(description = "心跳间期平均值")
    @TableField(value = "ibi_avg")
    private Integer ibiAvg;

    @Schema(description = "心率变异性指标")
    @TableField(value = "hrv_value")
    private BigDecimal hrvValue;

    @Schema(description = "呼吸幅值平均值")
    @TableField(value = "resp_amp_avg")
    private BigDecimal respAmpAvg;

    @Schema(description = "健康评分")
    @TableField(value = "health_score")
    private Integer healthScore;

    @Schema(description = "数据质量标记")
    @TableField(value = "quality_flag")
    private Integer qualityFlag;
}