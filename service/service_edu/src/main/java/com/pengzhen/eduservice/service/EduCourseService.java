package com.pengzhen.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pengzhen.commonutils.R;
import com.pengzhen.eduservice.entity.EduCourse;
import com.pengzhen.eduservice.entity.vo.CourseInfo;
import com.pengzhen.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author pengzhen
 * @since 2021-02-26
 */
public interface EduCourseService extends IService<EduCourse> {

    String addCourseInfoService(CourseInfo courseInfo);

    R updateCourseInfo(CourseInfo courseInfo);

    CourseInfo getCourseInfoByIdService(String courseId);

    CoursePublishVo getCoursePublishService(String courseId);

    void deleteCourseService(String id);
}
