package com.pengzhen.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pengzhen.cms.entity.EduBanner;
import com.pengzhen.cms.mapper.EduBannerMapper;
import com.pengzhen.cms.service.EduBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pengzhen
 * @since 2021-03-08
 */
@Service
public class EduBannerServiceImpl extends ServiceImpl<EduBannerMapper, EduBanner> implements EduBannerService {

    @Override
    public List<EduBanner> getAllBannerService() {
        QueryWrapper<EduBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 3");
        List<EduBanner> eduBanners = this.baseMapper.selectList(wrapper);
        return eduBanners;
    }
}
