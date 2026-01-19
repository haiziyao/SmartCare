package com.hzy.service;

import com.hzy.entity.MonthAvgParamInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 17542
* @description 针对表【month_avg_param_info(病人生理参数月均统计表)】的数据库操作Service
* @createDate 2026-01-13 06:26:16
*/
public interface MonthAvgParamInfoService extends IService<MonthAvgParamInfo> {

    void CalData(String date);
}
