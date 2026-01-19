package com.hzy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzy.entity.RoomInfo;
import com.hzy.result.Result;
import com.hzy.service.RoomInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@Tag(name = "房间管理")
@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    RoomInfoService service;

    @Operation(summary = "根据房间id查询房间信息")
    @GetMapping("/{id}")
    public Result<RoomInfo> getById(@PathVariable Long id) {
        RoomInfo result = service.getById(id);
        return Result.ok(result);
    }

    @Operation(summary = "分页查询房间信息")
    @GetMapping("/page")
    public Result<Page<RoomInfo>> getPage(@RequestParam long current, @RequestParam long size) {
        Page<RoomInfo> page = new Page<>(current, size);
        Page<RoomInfo> result = service.page(page);
        return Result.ok(result);
    }

    @Operation(summary = "新增或修改房间信息")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody RoomInfo roomInfo) {
        service.saveOrUpdate(roomInfo);
        return Result.ok();
    }

    @Operation(summary = "根据房间id删除房间信息")
    @DeleteMapping("/removeById")
    public Result removeById(@RequestParam Long id) {
        service.removeById(id);
        return Result.ok();
    }
}
