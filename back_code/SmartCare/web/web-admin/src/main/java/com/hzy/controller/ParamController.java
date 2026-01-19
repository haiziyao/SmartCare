package com.hzy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzy.entity.*;
import com.hzy.exception.MyException;
import com.hzy.result.Result;
import com.hzy.schedule.ParamSchedule;
import com.hzy.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import static com.hzy.result.ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR;

@Tag(name = "参数数据管理")
@RestController
@RequestMapping("/param")
public class ParamController {

    @Autowired
    ParamInfoService service;
    @Autowired
    DayAvgParamInfoService dayAvgParamInfoService;
    @Autowired
    WeekAvgParamInfoService weekAvgParamInfoService;
    @Autowired
    MonthAvgParamInfoService monthAvgParamInfoService;
    @Autowired
    YearAvgParamInfoService yearAvgParamInfoService;


    @Operation(summary = "分页查询param记录")
    @GetMapping("/page")
    public Result<Page<ParamInfo>> page(@RequestParam Long current,
                                        @RequestParam Long size,
                                        @RequestParam(required = false) Long patientId) {
        Page<ParamInfo> page = new Page<>(current, size);
        Page<ParamInfo> result;
        if (patientId != null) {
            LambdaQueryWrapper<ParamInfo> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ParamInfo::getPatientId, patientId);
            result = service.page(page, queryWrapper);
        }else{
         result =    service.page(page);
        }
        return Result.ok(result);
    }

    @Operation(summary = "新增一条数据记录")
    @PostMapping
    public Result<ParamInfo> save(@RequestBody ParamInfo paramInfo) {
        if (paramInfo.getId()!= null) paramInfo.setId(null);

        service.save(paramInfo);
        return Result.ok();
    }

    @Operation(summary = "分页查询DayAvgParam")
    @GetMapping("/day/page")
    public Result<Page<DayAvgParamInfo>> pageDay(@RequestParam Long current,
                                           @RequestParam Long size) {
        Page<DayAvgParamInfo> page = new Page<>(current, size);
        Page<DayAvgParamInfo> result = dayAvgParamInfoService.page(page);
        return Result.ok(result);
    }


    @Operation(summary = "分页查询WeekAvgParam")
    @GetMapping("/week/page")
    public Result<Page<WeekAvgParamInfo>> pageWeek(@RequestParam Long current,
                                                   @RequestParam Long size) {
        Page<WeekAvgParamInfo> page = new Page<>(current, size);
        Page<WeekAvgParamInfo> result = weekAvgParamInfoService.page(page);
        return Result.ok(result);
    }

    @Operation(summary = "分页查询MonthAvgParam")
    @GetMapping("/month/page")
    public Result<Page<MonthAvgParamInfo>> pageMonth(@RequestParam Long current,
                                                     @RequestParam Long size) {
        Page<MonthAvgParamInfo> page = new Page<>(current, size);
        Page<MonthAvgParamInfo> result = monthAvgParamInfoService.page(page);
        return Result.ok(result);
    }

    @Operation(summary = "分页查询YearAvgParam")
    @GetMapping("/year/page")
    public Result<Page<YearAvgParamInfo>> pageYear(@RequestParam Long current,
                                                   @RequestParam Long size) {
        Page<YearAvgParamInfo> page = new Page<>(current, size);
        Page<YearAvgParamInfo> result = yearAvgParamInfoService.page(page);
        return Result.ok(result);
    }

    @Value("${data_set_key}")
    private  String KEY_WORD;

    @Operation(summary = "手动进行执行day数据总结 ")
    @GetMapping("/data/day")
    public Result  CalDay(@RequestParam String keyword,
                          @Parameter(description = "统计日期，格式：YYYY-MM-DD", example = "2026-01-14")
                          @RequestParam(required = false)String date) {
        if (!KEY_WORD.equals(keyword)) {
            throw new MyException(ADMIN_CAPTCHA_CODE_ERROR);
        }

        dayAvgParamInfoService.CalData(date);
        return Result.ok( );
    }

    @Operation(summary = "手动进行执行week数据总结 ")
    @GetMapping("/data/week")
    public Result  CalWeek(@RequestParam String keyword,
                           @Parameter(description = "统计日期，格式：YYYY-MM-DD", example = "2026-01-14")
                           @RequestParam(required = false) String date) {
        if (!KEY_WORD.equals(keyword)) {
            throw new MyException(ADMIN_CAPTCHA_CODE_ERROR);
        }

        weekAvgParamInfoService.CalData(date);
        return Result.ok( );
    }

    @Operation(summary = "手动进行执行month数据总结 ")
    @GetMapping("/data/month")
    public Result  CalMonth(@RequestParam String keyword,
                            @Parameter(description = "统计日期，格式：YYYY-MM-DD", example = "2026-01-14")
                            @RequestParam(required = false) String date) {
        if (!KEY_WORD.equals(keyword)) {
            throw new MyException(ADMIN_CAPTCHA_CODE_ERROR);
        }

        monthAvgParamInfoService.CalData(date);
        return Result.ok( );
    }

    @Operation(summary = "手动进行执行year数据总结 ")
    @GetMapping("/data/year")
    public Result  CalYear(@RequestParam String keyword,
                           @Parameter(description = "统计日期，格式：YYYY-MM-DD", example = "2026-01-14")
                           @RequestParam(required = false) String date) {
        if (!KEY_WORD.equals(keyword)) {
            throw new MyException(ADMIN_CAPTCHA_CODE_ERROR);
        }

        yearAvgParamInfoService.CalData(date);
        return Result.ok( );
    }


}
