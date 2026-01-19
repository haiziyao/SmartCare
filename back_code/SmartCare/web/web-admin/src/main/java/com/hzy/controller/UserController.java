package com.hzy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzy.dto.UserInfoQueryDTO;
import com.hzy.entity.UserInfo;
import com.hzy.result.Result;
import com.hzy.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserInfoService service;

    @Operation(summary = "根据用户id查询用户信息")
    @GetMapping("/{id}")
    public Result<UserInfo> getById(@PathVariable Long id) {
        UserInfo result = service.getById(id);
        return Result.ok(result);
    }
    @Operation(summary = "分页匹配查询用户信息")
    @GetMapping("/page")
    public Result<Page<UserInfo>> getPage(@RequestParam long current, @RequestParam long size, UserInfoQueryDTO querydto ){
        Page<UserInfo> page = new Page<>(current, size);
        LambdaQueryWrapper<UserInfo> wrapper = new LambdaQueryWrapper();
        wrapper.like(querydto.getUsername() != null, UserInfo::getUsername, querydto.getUsername());
        wrapper.eq(querydto.getId() != null, UserInfo::getId, querydto.getId());
        Page<UserInfo> result = service.page(page, wrapper);
        return Result.ok(result);
    }

    @Operation(summary = "新增或修改用户信息")
    @PostMapping("/saveOrUpdate")
    public Result  saveOrUpdate(@RequestBody UserInfo userinfo) {
        service.saveOrUpdate(userinfo);
        return Result.ok();
    }

    @Operation(summary = "根据用户id删除用户")
    @DeleteMapping("/removeById")
    public Result  removeById(@RequestParam Long id) {
        service.removeById(id);
        return Result.ok();
    }
}
