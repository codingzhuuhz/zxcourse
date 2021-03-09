package com.pengzhen.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pengzhen.cms.entity.EduBanner;
import com.pengzhen.cms.service.EduBannerService;
import com.pengzhen.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pengzhen
 * @since 2021-03-08
 */
@RestController
@RequestMapping("cmsservice/adminbanner")
public class EduAdminBannerController {
    @Autowired
    private EduBannerService eduBannerService;
    @GetMapping("pageBanner/{current}/{pageCount}")
    public R pageBanner(@PathVariable long current, @PathVariable long pageCount){
        Page<EduBanner> pageBanner = new Page<>(current,pageCount);
        eduBannerService.page(pageBanner,null);
        long total = pageBanner.getTotal();
        List<EduBanner> banners = pageBanner.getRecords();

        return R.ok().data("total",total).data("banners",banners);
    }
    @DeleteMapping("deleteBanner/{id}")
    public R deleteBanner(@PathVariable String id){
        boolean b = eduBannerService.removeById(id);
        return b?R.ok(): R.error();
    }
    @PostMapping("updateBanner")
    public R updateBanner(@RequestBody EduBanner eduBanner){
        boolean res = eduBannerService.updateById(eduBanner);

        return res?R.ok():R.error();
    }
    @PostMapping("addBanner")
    public R addBanner(@RequestBody EduBanner eduBanner){
        boolean res = eduBannerService.save(eduBanner);
        return R.ok();
    }
}

