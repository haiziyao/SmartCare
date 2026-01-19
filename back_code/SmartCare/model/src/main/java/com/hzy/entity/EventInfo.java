package com.hzy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@TableName(value ="event_info")
@Data
@Schema(description = "设备上报异常事件")
public class EventInfo extends BaseEntity {



    @Schema(description = "设备ID")
    @TableField(value = "equipment_id")
    private Long equipmentId;

    @Schema(description = "病人ID")
    @TableField(value = "patient_id")
    private Long patientId;

    @Schema(description = "事件发生时间")
    @TableField(value = "occur_time")
    private Date occurTime;

    @Schema(description = "事件上报时间")
    @TableField(value = "report_time")
    private Date reportTime;

    @Schema(description = "事件类型：1-心率异常 2-呼吸异常 3-离床等")
    @TableField(value = "event_type")
    private Integer eventType;

    @Schema(description = "严重等级：1-轻度 2-中度 3-重度 4-危急")
    @TableField(value = "event_level")
    private Integer eventLevel;

    @Schema(description = "设备事件编码")
    @TableField(value = "event_code")
    private String eventCode;

    @Schema(description = "事件发生时心率")
    @TableField(value = "heart_rate")
    private Integer heartRate;

    @Schema(description = "事件发生时呼吸率")
    @TableField(value = "resp_rate")
    private Integer respRate;

    @Schema(description = "事件发生时心跳间期")
    @TableField(value = "ibi")
    private Integer ibi;

    @Schema(description = "事件发生时心率变异性指标")
    @TableField(value = "hrv_value")
    private BigDecimal hrvValue;

    @Schema(description = "事件发生时呼吸幅值")
    @TableField(value = "resp_amplitude")
    private BigDecimal respAmplitude;

    @Schema(description = "事件发生时健康评分")
    @TableField(value = "health_score")
    private Integer healthScore;

    @Schema(description = "事件详细描述")
    @TableField(value = "event_detail")
    private String eventDetail;

    @Schema(description = "原始扩展数据")
    @TableField(value = "raw_payload")
    private String rawPayload;

    @Schema(description = "处理状态：0-未处理 1-已确认 2-已处理 3-已忽略")
    @TableField(value = "handled_status")
    private Integer handledStatus;

    @Schema(description = "处理人ID")
    @TableField(value = "handler_staff_id")
    private Long handlerStaffId;

    @Schema(description = "处理时间")
    @TableField(value = "handled_time")
    private Date handledTime;

    @Schema(description = "通知状态：0-未通知 1-已通知")
    @TableField(value = "notify_status")
    private Integer notifyStatus;

    @Schema(description = "备注信息")
    @TableField(value = "remark")
    private String remark;
}