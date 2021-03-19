package com.pengzhen.order.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class constantOrderUtils implements InitializingBean {
    @Value("${appid}")
    private String appid;
    @Value("${mch_id}")
    private String mch_id;
    @Value("${notify_url}")
    private String notify_url;
    @Value("${partnerkey}")
    private String partnerkey;
    @Value("${wx_trade_url}")
    private String wx_trade_url;
    @Value("${wx_trade_status}")
    private String wx_trade_status;
    public static String APPID;
    public static String MCH_ID;
    public static String WX_TRADE_STATUS;

    public static String NOTIFY_URL;
    public static String PARTNERKEY;
    public static String WX_TRADE_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        constantOrderUtils.APPID = this.appid;
        constantOrderUtils.MCH_ID = this.mch_id;
        constantOrderUtils.NOTIFY_URL = this.notify_url ;
        constantOrderUtils.PARTNERKEY = this.partnerkey;
        constantOrderUtils.WX_TRADE_URL = this.wx_trade_url;
        constantOrderUtils.WX_TRADE_STATUS = this.wx_trade_status;
    }
}
