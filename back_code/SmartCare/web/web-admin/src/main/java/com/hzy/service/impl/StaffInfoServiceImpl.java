package com.hzy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.entity.StaffInfo;
import com.hzy.service.StaffInfoService;
import com.hzy.mapper.StaffInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 17542
* @description 针对表【staff_info(工作人员信息表)】的数据库操作Service实现
* @createDate 2026-01-13 06:26:17
*/
@Service
public class StaffInfoServiceImpl extends ServiceImpl<StaffInfoMapper, StaffInfo>
    implements StaffInfoService{

}




