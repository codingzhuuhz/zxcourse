package com.pengzhen.eduservice.service;

import com.pengzhen.eduservice.entity.EduCourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author pengzhen
 * @since 2021-02-26
 */
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {

    void removeCourseDescriptionService(String id);
}
