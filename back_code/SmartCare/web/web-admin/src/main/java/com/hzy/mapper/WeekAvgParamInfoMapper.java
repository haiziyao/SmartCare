package com.hzy.mapper;

import com.hzy.entity.WeekAvgParamInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 17542
* @description 针对表【week_avg_param_info(病人生理参数周均统计表)】的数据库操作Mapper
* @createDate 2026-01-13 06:26:17
* @Entity com.hzy.entity.WeekAvgParamInfo
*/
public interface WeekAvgParamInfoMapper extends BaseMapper<WeekAvgParamInfo> {

    void CalData(String targetData);
}




