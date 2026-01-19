package com.hzy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzy.entity.CaregiverInfo;
import com.hzy.entity.PatientInfo;
import com.hzy.result.Result;
import com.hzy.service.CaregiverInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "监护人管理")
@RestController
@RequestMapping("/caregiver")
public class CaregiverController {

    @Autowired
    CaregiverInfoService service;

    @Operation(summary = "根据监护人id查询监护人信息")
    @GetMapping("/{id}")
    public Result<CaregiverInfo> getById(@PathVariable Long id) {
        CaregiverInfo result = service.getById(id);
        return Result.ok(result);
    }
    @Operation(summary = "分页查询病人信息")
    @GetMapping("/page")
    public Result<Page<CaregiverInfo>> getPage(@RequestParam long current, @RequestParam long size) {
        Page<CaregiverInfo> page = new Page<>(current, size);
        Page<CaregiverInfo> result = service.page(page);
        return Result.ok(result);
    }

    @Operation(summary = "新增或修改监护人信息")
    @PostMapping("/saveOrUpdate")
    public Result  saveOrUpdate(@RequestBody CaregiverInfo caregiverInfo) {
        service.saveOrUpdate(caregiverInfo);
        return Result.ok();
    }

    @Operation(summary = "根据监护人id删除监护人")
    @DeleteMapping("/removeById")
    public Result  removeById(@RequestParam Long id) {
        service.removeById(id);
        return Result.ok();
    }
}
