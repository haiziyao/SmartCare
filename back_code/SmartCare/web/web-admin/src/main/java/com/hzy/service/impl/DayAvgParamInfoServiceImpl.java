package com.hzy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.entity.DayAvgParamInfo;
import com.hzy.service.DayAvgParamInfoService;
import com.hzy.mapper.DayAvgParamInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
* @author 17542
* @description 针对表【day_avg_param_info(病人生理参数日均统计表)】的数据库操作Service实现
* @createDate 2026-01-13 06:26:16
*/
@Service
public class DayAvgParamInfoServiceImpl extends ServiceImpl<DayAvgParamInfoMapper, DayAvgParamInfo>
    implements DayAvgParamInfoService{

    @Autowired
    private DayAvgParamInfoMapper mapper;

    @Override
    public void CalData(String date) {
        if (date == null || date.isEmpty()) {
            date = LocalDate.now().minusDays(1).toString();
        }
        mapper.CalData(date);
    }
}




