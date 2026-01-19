package com.hzy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.entity.MonthAvgParamInfo;
import com.hzy.service.MonthAvgParamInfoService;
import com.hzy.mapper.MonthAvgParamInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
* @author 17542
* @description 针对表【month_avg_param_info(病人生理参数月均统计表)】的数据库操作Service实现
* @createDate 2026-01-13 06:26:16
*/
@Service
public class MonthAvgParamInfoServiceImpl extends ServiceImpl<MonthAvgParamInfoMapper, MonthAvgParamInfo>
    implements MonthAvgParamInfoService{
    @Autowired
    private MonthAvgParamInfoMapper mapper;

    @Override
    public void CalData(String date) {
        if (date == null || date.isEmpty()) {
            date = LocalDate.now().minusMonths(1).toString();
            mapper.CalData(date);
        }
    }
}




