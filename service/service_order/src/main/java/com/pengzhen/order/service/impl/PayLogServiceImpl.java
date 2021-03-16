package com.pengzhen.order.service.impl;

import com.pengzhen.order.entity.PayLog;
import com.pengzhen.order.mapper.PayLogMapper;
import com.pengzhen.order.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author pengzhen
 * @since 2021-03-15
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

}
