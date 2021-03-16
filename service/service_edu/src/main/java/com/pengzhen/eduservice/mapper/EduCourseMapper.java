package com.pengzhen.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pengzhen.eduservice.entity.EduCourse;
import com.pengzhen.eduservice.entity.vo.CoursePublishVo;
import com.pengzhen.eduservice.entity.vo.frontVo.CourseFrontVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author pengzhen
 * @since 2021-02-26
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVo showCoursePublish(String courseId);

    CourseFrontVo getCourseFrontInfo(String id);
}
