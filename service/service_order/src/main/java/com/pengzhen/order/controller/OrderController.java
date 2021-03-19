package com.pengzhen.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pengzhen.commonutils.JwtUtils;
import com.pengzhen.commonutils.R;
import com.pengzhen.order.entity.Order;
import com.pengzhen.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author pengzhen
 * @since 2021-03-15
 */
@RestController
@CrossOrigin
@RequestMapping("/orderservice/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("saveOrder/{courseId}")
    public  R saveOrder(@PathVariable String courseId, HttpServletRequest request){
        String orderNo = orderService.generateOrder(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("orderNo",orderNo);
    }
    //根据订单的orderNo查询订单信息  这里还是可以根据订单的id 生成的
    @PostMapping("getOrder/{orderNo}")
    public R getOrder(@PathVariable String orderNo){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        Order order = orderService.getOne(wrapper);
        return R.ok().data("order",order);
    }
    /**
     * 通过courseId和memberId查询出来 此用户的买的课程是否付款成功，目的是在页面上显示 是需要购买 还是可以观看
     */
    @GetMapping("getUserBuyCourse/{courseId}/memberId")
    public R getUserBuyCourseOrder(@PathVariable String courseId,@PathVariable String memberId){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",memberId);
        Order order = orderService.getOne(wrapper);
        return R.ok().data("order",order);
    }
}

