package com.pengzhen.cms.controller;

import com.pengzhen.cms.entity.EduBanner;
import com.pengzhen.cms.service.EduBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("cmsservice/userbanner")
public class EduUserBannerController {
    @Autowired
    private EduBannerService eduBannerService;
    @Cacheable(key="'banners'",value="banners")
    @GetMapping("getAllBanner")
    public List<EduBanner> getAllBanner(){
        //轮播图显示
        List<EduBanner> banners = eduBannerService.getAllBannerService();
        return banners;
    }
}
