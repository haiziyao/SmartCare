package com.hzy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.entity.PatientInfo;
import com.hzy.service.PatientInfoService;
import com.hzy.mapper.PatientInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 17542
* @description 针对表【patient_info(病人基础信息表)】的数据库操作Service实现
* @createDate 2026-01-13 06:26:17
*/
@Service
public class PatientInfoServiceImpl extends ServiceImpl<PatientInfoMapper, PatientInfo>
    implements PatientInfoService{

}




