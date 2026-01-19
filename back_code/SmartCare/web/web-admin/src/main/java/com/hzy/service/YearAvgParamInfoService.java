package com.hzy.service;

import com.hzy.entity.YearAvgParamInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 17542
* @description 针对表【year_avg_param_info(病人生理参数年均统计表)】的数据库操作Service
* @createDate 2026-01-13 06:26:17
*/
public interface YearAvgParamInfoService extends IService<YearAvgParamInfo> {

    void CalData(String date);
}
