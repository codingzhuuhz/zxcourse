package com.pengzhen.eduservice.service;

import com.pengzhen.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author pengzhen
 * @since 2021-02-26
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeVideoService(String id);
}
