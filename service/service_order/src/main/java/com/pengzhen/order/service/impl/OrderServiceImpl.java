package com.pengzhen.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengzhen.commonutils.ordervo.CourseOrderVo;
import com.pengzhen.commonutils.ordervo.UserOrderVo;
import com.pengzhen.order.client.EduClient;
import com.pengzhen.order.client.UserClient;
import com.pengzhen.order.entity.Order;
import com.pengzhen.order.mapper.OrderMapper;
import com.pengzhen.order.service.OrderService;
import com.pengzhen.order.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author pengzhen
 * @since 2021-03-15
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private EduClient eduClient;
    @Autowired
    private UserClient userClient;
    @Override
    public String generateOrder(String courseId, String memberIdByJwtToken) {
        CourseOrderVo course = eduClient.getCourse(courseId);
        UserOrderVo userInfo = userClient.getUserInfo(memberIdByJwtToken);
        Order order = new Order();
        order.setCourseId(course.getId());
        order.setCourseCover(course.getCover());
        order.setCourseTitle(course.getTitle());
        order.setGmtCreate(new Date());
        order.setGmtModified(new Date());
        order.setMemberId(userInfo.getId());
        order.setMobile(userInfo.getMobile());
        order.setNickname(userInfo.getNickname());
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setPayType(1);
        order.setStatus(0);
        order.setTeacherName(course.getTeacherName());
        this.baseMapper.insert(order);
        return order.getOrderNo();
    }
}
