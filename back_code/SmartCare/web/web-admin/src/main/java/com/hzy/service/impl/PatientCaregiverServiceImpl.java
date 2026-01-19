package com.hzy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.entity.PatientCaregiver;
import com.hzy.service.PatientCaregiverService;
import com.hzy.mapper.PatientCaregiverMapper;
import org.springframework.stereotype.Service;

/**
* @author 17542
* @description 针对表【patient_caregiver(病人-监护人关系表)】的数据库操作Service实现
* @createDate 2026-01-13 06:26:17
*/
@Service
public class PatientCaregiverServiceImpl extends ServiceImpl<PatientCaregiverMapper, PatientCaregiver>
    implements PatientCaregiverService{

}




