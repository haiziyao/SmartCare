package com.hzy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.Data;

/**
 * 设备信息表
 * @TableName equipment_info
 */
@TableName(value ="equipment_info")
@Data
@Schema(description = "设备信息")
public class EquipmentInfo extends BaseEntity {



    /**
     * 设备唯一编码（SN/IMEI/MAC等）
     */
    @Schema(description = "设备唯一编码")
    @TableField(value = "code")
    private String equipmentCode;

    /**
     * 设备类型
     */
    @Schema(description = "设备类型")
    @TableField(value = "type")
    private Integer equipmentType;

    /**
     * 当前绑定的病人ID
     */
    @Schema(description = "绑定病人ID")
    @TableField(value = "patient_id")
    private Long patientId;

    /**
     * 设备所在房间ID
     */
    @Schema(description = "房间ID")
    @TableField(value = "room_id")
    private Long roomId;

    /**
     * 设备最近一次绑定病人的时间
     */
    @Schema(description = "最近绑定时间")
    @TableField(value = "bind_time")
    private Date bindTime;

    /**
     * 设备最近一次解绑病人的时间
     */
    @Schema(description = "最近解绑时间")
    @TableField(value = "unbind_time")
    private Date unbindTime;

    /**
     * 设备当前状态
     */
    @Schema(description = "设备状态")
    @TableField(value = "equipment_status")
    private Integer equipmentStatus;

    /**
     * 设备最近一次在线时间
     */
    @Schema(description = "最近在线时间")
    @TableField(value = "last_online_time")
    private Date lastOnlineTime;

    /**
     * 设备最近一次数据上报时间
     */
    @Schema(description = "最近上报时间")
    @TableField(value = "last_report_time")
    private Date lastReportTime;

    /**
     * 设备固件版本号
     */
    @Schema(description = "固件版本")
    @TableField(value = "firmware_version")
    private String firmwareVersion;

    /**
     * 通信协议版本号
     */
    @Schema(description = "协议版本")
    @TableField(value = "protocol_version")
    private String protocolVersion;

    /**
     * 设备备注信息
     */
    @Schema(description = "备注")
    @TableField(value = "remark")
    private String remark;
}