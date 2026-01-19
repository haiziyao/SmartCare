package com.hzy.service;

import com.hzy.entity.WeekAvgParamInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 17542
* @description 针对表【week_avg_param_info(病人生理参数周均统计表)】的数据库操作Service
* @createDate 2026-01-13 06:26:17
*/
public interface WeekAvgParamInfoService extends IService<WeekAvgParamInfo> {

    void CalData(String date);
}
