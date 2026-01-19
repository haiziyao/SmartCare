package com.hzy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzy.entity.EventInfo;
import com.hzy.service.EventInfoService;
import com.hzy.mapper.EventInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 17542
* @description 针对表【event_info(设备上报异常事件表)】的数据库操作Service实现
* @createDate 2026-01-13 06:26:16
*/
@Service
public class EventInfoServiceImpl extends ServiceImpl<EventInfoMapper, EventInfo>
    implements EventInfoService{

}




