package com.pengzhen.user.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantUserUtils implements InitializingBean {
    @Value("${WX_OPEN_APP_ID}")
    private String wx_open_app_id;
    @Value("${WX_OPEN_REDIRECT_URL}")
    private String wx_open_redirect_url;
    public static String WX_OPEN_APP_ID;
    public static String WX_OPEN_REDIRECT_URL;
    @Override
    public void afterPropertiesSet() throws Exception {
        ConstantUserUtils.WX_OPEN_APP_ID = this.wx_open_app_id;
        ConstantUserUtils.WX_OPEN_REDIRECT_URL = this.wx_open_redirect_url;
    }
}
