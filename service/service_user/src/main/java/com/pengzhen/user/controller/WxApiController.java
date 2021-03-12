package com.pengzhen.user.controller;

import com.pengzhen.user.utils.ConstantUserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
@RequestMapping("/userservice/wxuser")
public class WxApiController {
    @GetMapping("login")
    public String login(){
        //第一种拼装字符串的方法
//        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect?" +
//                "appid=APPID&" +
//                "redirect_uri=REDIRECT_URI&" +
//                "response_type=code&" +
//                "scope=snsapi_login&" +
//                "state=STATE" +
//                "#wechat_redirect";
        //第二种拼装字符转的方法
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect?" +
                "appid=%s&" +
                "redirect_uri=%s&" +
                "response_type=code&" +
                "scope=snsapi_login" +
                "state=%s" +
                "#wechat_redirect";
        String url = String.format(baseUrl, ConstantUserUtils.WX_OPEN_APP_ID, ConstantUserUtils.WX_OPEN_REDIRECT_URL, "haha");
        return "redirect:"+baseUrl;
    }

}
