package com.hzy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.entity.WeekAvgParamInfo;
import com.hzy.service.WeekAvgParamInfoService;
import com.hzy.mapper.WeekAvgParamInfoMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
* @author 17542
* @description 针对表【week_avg_param_info(病人生理参数周均统计表)】的数据库操作Service实现
* @createDate 2026-01-13 06:26:17
*/
@Service
public class WeekAvgParamInfoServiceImpl extends ServiceImpl<WeekAvgParamInfoMapper, WeekAvgParamInfo>
    implements WeekAvgParamInfoService{
    @Autowired
    private WeekAvgParamInfoMapper mapper;

    @Override
    public void CalData(String date) {
        if (date == null || date.isEmpty()) {
            date = LocalDate.now().minusWeeks(1).toString();
        }
        mapper.CalData(date);
    }
}




