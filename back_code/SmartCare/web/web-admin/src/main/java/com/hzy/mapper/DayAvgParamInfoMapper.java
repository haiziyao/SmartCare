package com.hzy.mapper;

import com.hzy.entity.DayAvgParamInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 17542
* @description 针对表【day_avg_param_info(病人生理参数日均统计表)】的数据库操作Mapper
* @createDate 2026-01-13 06:26:16
* @Entity com.hzy.entity.DayAvgParamInfo
*/
public interface DayAvgParamInfoMapper extends BaseMapper<DayAvgParamInfo> {

    void CalData(String targetDate);
}




