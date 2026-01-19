package com.hzy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.entity.ParamInfo;
import com.hzy.service.ParamInfoService;
import com.hzy.mapper.ParamInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 17542
* @description 针对表【param_info(病人生理参数统计结果表（设备端算法输出）)】的数据库操作Service实现
* @createDate 2026-01-13 06:26:17
*/
@Service
public class ParamInfoServiceImpl extends ServiceImpl<ParamInfoMapper, ParamInfo>
    implements ParamInfoService{

}




