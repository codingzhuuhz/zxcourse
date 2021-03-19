package com.pengzhen.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wxpay.sdk.WXPayUtil;
import com.pengzhen.order.entity.Order;
import com.pengzhen.order.entity.PayLog;
import com.pengzhen.order.mapper.PayLogMapper;
import com.pengzhen.order.service.OrderService;
import com.pengzhen.order.service.PayLogService;
import com.pengzhen.order.utils.HttpClient;
import com.pengzhen.order.utils.constantOrderUtils;
import com.pengzhen.servicebase.exception.zxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    @Autowired
    private OrderService orderService;
    @Override
    public Map createQRcode(String orderNo) {
        //根据订单号查询订单信息
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        Order order = orderService.getOne(wrapper);

        //使用map设置生成二维码需要的参数，map格式好利用微信的为的工具类进行转换
        Map<String,String> map = new HashMap<>();
        map.put("appid", constantOrderUtils.APPID);
        map.put("mch_id",constantOrderUtils.MCH_ID);
        map.put("nonce_str", WXPayUtil.generateNonceStr());
        map.put("body",order.getCourseTitle());//用于显示课程的标题
        map.put("out_trade_no",orderNo);//订单号
        //
        map.put("total_fee",order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");
        map.put("spbill_create_ip","127.0.0.1");
        map.put("notify_url",constantOrderUtils.NOTIFY_URL);
        map.put("trade_type","NATIVE");//


//        map.put("")
        //发送httpClient请求 需要传递xml格式的数据，将map数据转换为xml格式
        HttpClient client = new HttpClient(constantOrderUtils.WX_TRADE_URL);
        try {
            //商戶的商户的是用来对用map中的信息进行加密的
            client.setXmlParam(WXPayUtil.generateSignedXml(map,constantOrderUtils.PARTNERKEY));
            client.setHttps(true);//设置支持微信支付
            client.post();
            //返回的数据进行操作
            String contentXML = client.getContent();//得到返回的数据，返回的是xml格式的数据
            Map<String, String> resultMap = WXPayUtil.xmlToMap(contentXML);
            Map<Object, Object> m = new HashMap<>();
            m.put("out_trade_no",orderNo);
            m.put("course_id",order.getCourseId());
            m.put("total_fee",order.getTotalFee());
            m.put("trade_status_code",resultMap.get("result_code"));
            m.put("code_url",resultMap.get("code_url"));
            return m;
        } catch (Exception e) {
           throw new zxException(20001,"支付二維碼生成失败");
        }
    }
    //查询订单的支付状态
    @Override
    public Map<String, String> queryPayStatus(String orderNo) {
        Map<String,String> map = new HashMap<>();
        map.put("appid", constantOrderUtils.APPID);
        map.put("mch_id",constantOrderUtils.MCH_ID);
        map.put("nonce_str", WXPayUtil.generateNonceStr());
        map.put("out_trade_no",orderNo);//订单号
        HttpClient client = new HttpClient(constantOrderUtils.WX_TRADE_STATUS);
            //商戶的商户的是用来对用map中的信息进行加密的
        try {
            client.setXmlParam(WXPayUtil.generateSignedXml(map,constantOrderUtils.PARTNERKEY));
            client.setHttps(true);//设置支持微信支付
            client.post();
            String contentXML = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(contentXML);
            return resultMap;
        } catch (Exception e) {
            throw new zxException(20001,"订单状态查询失败");
        }
    }
    //支付成功之后，更新订单中的状态，并想支付表中添加一条记录
    @Override
    public void updateOrder(Map<String,String> map) {
        String orderNo = map.get("out_trade_no");
        //根据订单号查询订单信息
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        Order order = orderService.getOne(wrapper);
        //Integer.intValue()
        if(order.getStatus().intValue()==1){
            return;
        }
        order.setStatus(1);//自動装箱和拆箱
        orderService.updateById(order);
        PayLog payLog = new PayLog();
        payLog.setPayTime(new Date());//设置支付完成时间
        payLog.setGmtCreate(new Date());
        payLog.setGmtModified(new Date());
        payLog.setOrderNo(orderNo);
        payLog.setTotalFee(order.getTotalFee());
        payLog.setTradeState(map.get("trade_state"));
        payLog.setTransactionId(map.get("transaction_id"));//设置交易的流水号
        payLog.setAttr(JSONObject.toJSONString(map));//支付的其他的一些信息放到attr里面
        this.baseMapper.insert(payLog);
    }
}
