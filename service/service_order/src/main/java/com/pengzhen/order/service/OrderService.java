package com.pengzhen.order.service;

import com.pengzhen.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author pengzhen
 * @since 2021-03-15
 */
public interface OrderService extends IService<Order> {

    String generateOrder(String courseId, String memberIdByJwtToken);
}
