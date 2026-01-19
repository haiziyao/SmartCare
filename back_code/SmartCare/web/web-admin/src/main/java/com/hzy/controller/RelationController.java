package com.hzy.controller;

import com.hzy.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "病人与监护人关系管理")
@RestController
@RequestMapping("/relation")
public class RelationController {

    @Operation(summary = "根据病人id查询监护人信息")
    @GetMapping("/patient/{id}")
    public Result<?> listCaregiverByPatientId(@PathVariable Long id) {
        return Result.ok();
    }

    @Operation(summary = "新增或修改关系")
    @PostMapping("/saveOrUpdate")
    public Result<?> saveOrUpdate(@RequestBody Object relationVo) {
        return Result.ok();
    }

    @Operation(summary = "删除关系")
    @DeleteMapping("/remove")
    public Result<?> remove(@RequestParam Long patientId,
                            @RequestParam Long caregiverId) {
        return Result.ok();
    }
}
