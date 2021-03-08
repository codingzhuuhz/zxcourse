package com.pengzhen.ossservice.controller;

import com.pengzhen.commonutils.R;
import com.pengzhen.ossservice.service.ossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("ossservice/upload")
public class ossController {
    @Autowired
    private ossService ossService;
    @PostMapping("/avatar")
    public R uploadAvatar(MultipartFile file){
        String url = ossService.uploadAvatarService(file);
        return R.ok().data("url",url);
    }
}
