package com.hzy.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 作者：亥子曜
 * -后之览者，亦将有感于斯文
 */
@Data
public class UserInfoQueryDTO {
    @TableId(value = "user_id", type = IdType.AUTO)
    @Schema(description = "用户ID")
    private Long id;

    @Schema(description = "登录用户名")
    @TableField(value = "username")
    private String username;
}
