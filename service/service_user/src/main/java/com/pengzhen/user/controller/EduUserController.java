package com.pengzhen.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pengzhen.commonutils.JwtUtils;
import com.pengzhen.commonutils.MD5;
import com.pengzhen.commonutils.R;
import com.pengzhen.servicebase.exception.zxException;
import com.pengzhen.user.entity.EduUser;
import com.pengzhen.user.entity.vo.UserQuery;
import com.pengzhen.user.service.EduUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author pengzhen
 * @since 2021-03-11
 */
@CrossOrigin
@RestController
@RequestMapping("/userservice/user")
public class EduUserController {
    @Autowired
    private EduUserService eduUserService;
    @PostMapping("login")
    public String login(@RequestBody UserQuery userQuery){
        String code = userQuery.getCode();
        String phone = userQuery.getPhone();
        String password = userQuery.getPassword();
        String nickname = userQuery.getNickname();

//        if(StringUtils.isEmpty(code)){
//            throw new zxException(20001,"验证码为空");
//        }
        if(StringUtils.isEmpty(phone)){
            throw new zxException(20001,"手机号为空");
        }

        if(StringUtils.isEmpty(password)){
           throw new zxException(20001,"密码为空");
        }
        //都不为空之后 ，根据手机号进行查询
        QueryWrapper<EduUser> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",phone);
        EduUser user = eduUserService.getOne(wrapper);
        if(user==null){
            throw new zxException(20001,"登录失败");
        }
        //判断数据库中的密码是否正确
        if(!MD5.encrypt(password).equals(user.getPassword())){
            throw new zxException(20001,"密码不正确");
        }
        //判断用户的账户是否被禁用状态
        if(user.getDisabled()){
            throw new zxException(20001,"用户被禁用");
        }
        //成功登录


        //生成token令牌，多服务登录 进行单点登录
        String token = JwtUtils.getJwtToken(user.getId(), user.getNickname());
        return token;
    }
    @PostMapping("register")
    public boolean register(@RequestBody UserQuery userQuery){
        String nickname = userQuery.getNickname();
        String mobile = userQuery.getPhone();
        String code = userQuery.getCode();
        String password = userQuery.getPassword();
        QueryWrapper<EduUser> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        EduUser user = eduUserService.getOne(wrapper);
        if(user!=null){
            throw new zxException(20001,"手机号已被注册");
        }
        //这个地方用来判断code

        EduUser eduUser = new EduUser();
        eduUser.setPassword(MD5.encrypt(password));
        eduUser.setMobile(mobile);
        eduUser.setNickname(nickname);
        eduUser.setGmtCreate(new Date());
        eduUser.setGmtModified(new Date());
        boolean res = eduUserService.save(eduUser);
        return res;
    }
    //为什么不在登录的时候直接返回用户的信息？还要在查询一次？
    @GetMapping("getUserInfo")
    public R getUserInfoByToken(HttpServletRequest request){
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        QueryWrapper<EduUser> wrapper = new QueryWrapper<>();
        EduUser user = eduUserService.getOne(wrapper);

        return R.ok().data("user",user);
    }
}

