package com.hzy.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 病人生理参数年均统计表
 * @TableName year_avg_param_info
 */
@TableName(value ="year_avg_param_info")
@Data
@Schema(description = "病人生理参数年均统计")
public class YearAvgParamInfo extends BaseEntity {

    @Schema(description = "病人ID")
    @TableField(value = "patient_id")
    private Long patientId;

    @Schema(description = "统计年份")
    @TableField(value = "stat_year")
    private Integer statYear;

    @Schema(description = "当年心率平均值（bpm）")
    @TableField(value = "hr_avg")
    private Integer hrAvg;

    @Schema(description = "当年心率平均偏差（%）")
    @TableField(value = "hr_diff_avg")
    private BigDecimal hrDiffAvg;

    @Schema(description = "当年呼吸率平均值")
    @TableField(value = "rr_avg")
    private Integer rrAvg;

    @Schema(description = "当年呼吸率平均偏差（%）")
    @TableField(value = "rr_diff_avg")
    private BigDecimal rrDiffAvg;

    @Schema(description = "当年心跳间期平均值")
    @TableField(value = "ibi_avg")
    private Integer ibiAvg;

    @Schema(description = "当年HRV平均值")
    @TableField(value = "hrv_avg")
    private BigDecimal hrvAvg;

    @Schema(description = "当年呼吸幅值平均值")
    @TableField(value = "resp_amp_avg")
    private BigDecimal respAmpAvg;

    @Schema(description = "当年健康评分平均值")
    @TableField(value = "health_score_avg")
    private Integer healthScoreAvg;
}