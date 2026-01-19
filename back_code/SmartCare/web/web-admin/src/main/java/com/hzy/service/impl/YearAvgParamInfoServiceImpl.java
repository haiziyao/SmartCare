package com.hzy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.entity.YearAvgParamInfo;
import com.hzy.service.YearAvgParamInfoService;
import com.hzy.mapper.YearAvgParamInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
* @author 17542
* @description 针对表【year_avg_param_info(病人生理参数年均统计表)】的数据库操作Service实现
* @createDate 2026-01-13 06:26:17
*/
@Service
public class YearAvgParamInfoServiceImpl extends ServiceImpl<YearAvgParamInfoMapper, YearAvgParamInfo>
    implements YearAvgParamInfoService{
    @Autowired
    private YearAvgParamInfoMapper mapper;

    @Override
    public void CalData(String date) {
        if (date == null || date.isEmpty()) {
            date = LocalDate.now().minusYears(1).toString();
        }
        mapper.CalData(date);
    }
}




