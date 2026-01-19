package com.hzy.mapper;

import com.hzy.entity.MonthAvgParamInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 17542
* @description 针对表【month_avg_param_info(病人生理参数月均统计表)】的数据库操作Mapper
* @createDate 2026-01-13 06:26:16
* @Entity com.hzy.entity.MonthAvgParamInfo
*/
public interface MonthAvgParamInfoMapper extends BaseMapper<MonthAvgParamInfo> {

    void CalData(String targetDate);
}




