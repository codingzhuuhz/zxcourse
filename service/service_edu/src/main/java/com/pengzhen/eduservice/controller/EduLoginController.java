package com.pengzhen.eduservice.controller;

import com.pengzhen.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin//跨域注解
public class EduLoginController {
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","token");
    }
    @GetMapping("info")
    public R getInfo(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","");
    }
}
