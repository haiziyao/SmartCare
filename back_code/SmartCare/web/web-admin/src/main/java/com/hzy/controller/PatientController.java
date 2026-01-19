package com.hzy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzy.entity.PatientInfo;
import com.hzy.result.Result;
import com.hzy.service.PatientInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "病人管理")
@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    PatientInfoService service;

    @Operation(summary = "根据病人id查询病人信息")
    @GetMapping("/{id}")
    public Result<PatientInfo> getById(@PathVariable Long id) {
        PatientInfo result = service.getById(id);
        return Result.ok(result);
    }
    @Operation(summary = "分页查询病人信息")
    @GetMapping("/page")
    public Result<Page<PatientInfo>> getPage(@RequestParam long current, @RequestParam long size) {
        Page<PatientInfo> page = new Page<>(current, size);
        Page<PatientInfo> result = service.page(page);
        return Result.ok(result);
    }

    @Operation(summary = "新增或修改病人信息")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody PatientInfo patientInfo) {
        service.saveOrUpdate(patientInfo);
        return Result.ok();
    }

    @Operation(summary = "根据病人id删除病人信息")
    @DeleteMapping("/removeById")
    public Result removeById(@RequestParam Long id) {
        service.removeById(id);
        return Result.ok();
    }
}
