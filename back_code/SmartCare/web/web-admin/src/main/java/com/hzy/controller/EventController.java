package com.hzy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzy.entity.EventInfo;
import com.hzy.result.Result;
import com.hzy.service.EventInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "事件管理")
@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventInfoService service;

    @Operation(summary = "新增报警信息")
    @PostMapping("/save")
    public Result  save(@RequestBody EventInfo eventInfo) {
        service.save(eventInfo);
        return Result.ok();
    }

    @Operation(summary = "根据病人id查询事件")
    @GetMapping("/{id}")
    public Result<EventInfo> listByPatientId(@PathVariable Long id) {
        EventInfo result = service.getById(id);
        return Result.ok();
    }

    @Operation(summary = "分页查询事件信息")
    @GetMapping("/page")
    public Result<Page<EventInfo>> page(@RequestParam Long current,
                          @RequestParam Long size) {
        Page<EventInfo> result = new Page<>(current, size);
        Page<EventInfo> page = service.page(result);
        return Result.ok(page);
    }
}
