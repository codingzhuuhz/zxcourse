package com.pengzhen.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.pengzhen.commonutils.JwtUtils;
import com.pengzhen.servicebase.exception.zxException;
import com.pengzhen.user.entity.EduUser;
import com.pengzhen.user.service.EduUserService;
import com.pengzhen.user.utils.ConstantUserUtils;
import com.pengzhen.user.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.HashMap;

@Controller
@CrossOrigin
@RequestMapping("/api/ucenter/wx")
public class WxApiController {
    @Autowired
    private EduUserService eduUserService;
//使用code 来获取access_token
    @GetMapping("callback")
    public String callback(String code,String state){
        String baseUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=%s&" +
                "secret=%s&" +
                "code=%s&" +
                "grant_type=authorization_code";
        String url = String.format(
                baseUrl,
                ConstantUserUtils.WX_OPEN_APP_ID,
                ConstantUserUtils.WX_OPEN_APP_SECRET,
                code
        );

        try {
            String res = HttpClientUtils.get(url);
            Gson gson = new Gson();
            HashMap map = gson.fromJson(res, HashMap.class);
            String access_token = (String) map.get("access_token");
            String openid = (String)map.get("openid");

            QueryWrapper<EduUser> wrapper = new QueryWrapper<>();
            wrapper.eq("openid",openid);
            EduUser one = eduUserService.getOne(wrapper);
            if(one==null){
                String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid;
                String userInfo = HttpClientUtils.get(userInfoUrl);

                HashMap user = gson.fromJson(userInfo, HashMap.class);
                one = new EduUser();//这个可以不用定义一个新的变量了
                one.setOpenid((String) user.get("openid"));
                one.setNickname((String) user.get("nickname"));
                one.setAvatar((String)user.get("headimgurl"));
                one.setSex((Integer) user.get("sex"));
                one.setGmtCreate(new Date());
                one.setGmtModified(new Date());
                eduUserService.save(one);
            }

            String token = JwtUtils.getJwtToken(one.getId(), one.getNickname());
            return "redirect:http://localhost:3000?token="+token;
        } catch (Exception e) {
           throw new zxException(20001,"登录失败");
        }
    }
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
                "scope=snsapi_login&" +
                "state=%s" +
                "#wechat_redirect";
        String url = String.format(baseUrl, ConstantUserUtils.WX_OPEN_APP_ID, ConstantUserUtils.WX_OPEN_REDIRECT_URL, "atguigu");
        return "redirect:"+url;
    }

}
