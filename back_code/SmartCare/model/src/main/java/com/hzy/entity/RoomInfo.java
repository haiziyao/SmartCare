package com.hzy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@TableName(value ="room_info")
@Data
@Schema(description = "房间信息")
public class RoomInfo extends BaseEntity {



    @Schema(description = "房间编号")
    @TableField(value = "room_code")
    private String roomCode;

    @Schema(description = "房间名称")
    @TableField(value = "room_name")
    private String roomName;

    @Schema(description = "房间类型")
    @TableField(value = "room_type")
    private Integer roomType;

    @Schema(description = "最大容纳数")
    @TableField(value = "max_size")
    private Integer maxSize;

    @Schema(description = "当前占用数")
    @TableField(value = "used_size")
    private Integer usedSize;

    @Schema(description = "房间状态")
    @TableField(value = "room_status")
    private Integer roomStatus;

    @Schema(description = "备注信息")
    @TableField(value = "remark")
    private String remark;
}