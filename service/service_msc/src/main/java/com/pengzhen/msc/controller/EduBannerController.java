package com.pengzhen.msc.controller;


import com.pengzhen.commonutils.R;
import com.pengzhen.msc.entity.EduBanner;
import com.pengzhen.msc.service.EduBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pengzhen
 * @since 2021-03-08
 */
@RestController
@RequestMapping("/msc/edu-banner")
public class EduBannerController {
    @Autowired
    private EduBannerService eduBannerService;
    @GetMapping("test/{id}")
    public R test(@PathVariable long id){
        EduBanner byId = eduBannerService.getById(id);
        return R.ok();

    }
}

