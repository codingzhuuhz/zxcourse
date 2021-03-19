package com.pengzhen.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pengzhen.order.entity.PayLog;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author pengzhen
 * @since 2021-03-15
 */
public interface PayLogService extends IService<PayLog> {

    Map createQRcode(String orderNo);

    Map<String, String> queryPayStatus(String orderNo);

    void updateOrder(Map<String,String> map);
}
