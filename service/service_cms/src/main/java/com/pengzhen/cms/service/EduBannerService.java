package com.pengzhen.cms.service;

import com.pengzhen.cms.entity.EduBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pengzhen
 * @since 2021-03-08
 */
public interface EduBannerService extends IService<EduBanner> {

    List<EduBanner> getAllBannerService();
}
