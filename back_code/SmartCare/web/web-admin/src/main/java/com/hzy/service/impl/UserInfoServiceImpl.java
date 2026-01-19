package com.hzy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.entity.UserInfo;
import com.hzy.service.UserInfoService;
import com.hzy.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 17542
* @description 针对表【user_info(系统用户账号表)】的数据库操作Service实现
* @createDate 2026-01-13 06:26:17
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

}




