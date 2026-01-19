package com.hzy.schedule;

import com.hzy.service.*;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 作者：亥子曜
 * -后之览者，亦将有感于斯文
 */

@Component
public class ParamSchedule {
    @Autowired
    private DayAvgParamInfoService dayAvgParamInfoService;
    @Autowired
    private WeekAvgParamInfoService weekAvgParamInfoService;
    @Autowired
    private MonthAvgParamInfoService monthAvgParamInfoService;
    @Autowired
    private YearAvgParamInfoService yearAvgParamInfoService;

    // 秒 分 时 日 月 周
    @Scheduled(cron = "0 0 0 ? * 1")
    public void CalDay(){
        dayAvgParamInfoService.CalData(null);
    }
    @Scheduled(cron = "0 30 0 ? * 1")
    public void CalWeek(){
        weekAvgParamInfoService.CalData(null);
    }
    @Scheduled(cron = "0 35 0 1 * ?")
    public void CalMonth(){
        monthAvgParamInfoService.CalData(null);
    }
    @Scheduled(cron = "0 40 0 1 1 ?")
    public void CalYear(){
        yearAvgParamInfoService.CalData(null);
    }
}
