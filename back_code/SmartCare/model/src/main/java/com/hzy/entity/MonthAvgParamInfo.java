package com.hzy.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@TableName(value ="month_avg_param_info")
@Data
@Schema(description = "病人生理参数月均统计")
public class MonthAvgParamInfo extends BaseEntity {

    @Schema(description = "病人ID")
    @TableField(value = "patient_id")
    private Long patientId;

    @Schema(description = "统计年份")
    @TableField(value = "stat_year")
    private Integer statYear;

    @Schema(description = "统计月份")
    @TableField(value = "stat_month")
    private Integer statMonth;

    @Schema(description = "当月心率平均值")
    @TableField(value = "hr_avg")
    private Integer hrAvg;

    @Schema(description = "当月心率平均偏差")
    @TableField(value = "hr_diff_avg")
    private BigDecimal hrDiffAvg;

    @Schema(description = "当月呼吸率平均值")
    @TableField(value = "rr_avg")
    private Integer rrAvg;

    @Schema(description = "当月呼吸率平均偏差")
    @TableField(value = "rr_diff_avg")
    private BigDecimal rrDiffAvg;

    @Schema(description = "当月心跳间期平均值")
    @TableField(value = "ibi_avg")
    private Integer ibiAvg;

    @Schema(description = "当月HRV平均值")
    @TableField(value = "hrv_avg")
    private BigDecimal hrvAvg;

    @Schema(description = "当月呼吸幅值平均值")
    @TableField(value = "resp_amp_avg")
    private BigDecimal respAmpAvg;

    @Schema(description = "当月健康评分平均值")
    @TableField(value = "health_score_avg")
    private Integer healthScoreAvg;
}