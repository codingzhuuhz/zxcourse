package com.pengzhen.order.controller;

import com.pengzhen.commonutils.JwtUtils;
import com.pengzhen.commonutils.R;
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
}

