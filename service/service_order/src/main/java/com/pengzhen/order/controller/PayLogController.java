package com.pengzhen.order.controller;


import com.pengzhen.commonutils.R;
import com.pengzhen.order.service.PayLogService;
import com.pengzhen.servicebase.exception.zxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author pengzhen
 * @since 2021-03-15
 */
@RestController
@CrossOrigin
@RequestMapping("/orderservice/paylog")
public class PayLogController {
    @Autowired
    private PayLogService payLogService;
    //生成支付的二维码
    @GetMapping("createQRcode/{orderNo}")
    public R createQRcode(@PathVariable String orderNo){
        Map map = payLogService.createQRcode(orderNo);
        return R.ok().data(map);
    }
    //查询订单的支付状态,修改订单表中的状态，像支付表pay_log中加入一条支付记录
    @GetMapping("queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo){
        Map<String,String> map = payLogService.queryPayStatus(orderNo);
        if(map==null){
            throw new zxException(20001,"订单支付失败");
        }
        if(map.get("trade_state").equals("SUCCESS")){
            //更新订单表中的状态,往支付记录表中添加一条数据
            payLogService.updateOrder(map);
            return R.ok().message("订单支付成功");
        }
        return R.ok().code(50000).message("支付中...");
    }
}

