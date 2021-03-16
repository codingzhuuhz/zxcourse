package com.pengzhen.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pengzhen.commonutils.R;
import com.pengzhen.commonutils.ordervo.CourseOrderVo;
import com.pengzhen.eduservice.entity.EduCourse;
import com.pengzhen.eduservice.entity.vo.CourseInfo;
import com.pengzhen.eduservice.entity.vo.CoursePublishVo;
import com.pengzhen.eduservice.entity.vo.frontVo.CourseFrontVo;
import com.pengzhen.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author pengzhen
 * @since 2021-02-26
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfo courseInfo){
        String courseId = eduCourseService.addCourseInfoService(courseInfo);
        return R.ok().data("courseId",courseId);
    }
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfo courseInfo){
        R r = eduCourseService.updateCourseInfo(courseInfo);

        return r;
    }
    @GetMapping("getCourseInfoById/{courseId}")
    public R getCourseInfoById(@PathVariable String courseId){
        CourseInfo eduCourseInfo = eduCourseService.getCourseInfoByIdService(courseId);
        return R.ok().data("courseInfo",eduCourseInfo);
    }
    @GetMapping("getCoursePublish/{courseId}")
    public R getCoursePublish(@PathVariable String courseId){
        CoursePublishVo coursePublishVo = eduCourseService.getCoursePublishService(courseId);
        return R.ok().data("coursePublish",coursePublishVo);
    }
    @PostMapping("publishCourse/{courseId}")
    public R publicCourse(@PathVariable String courseId){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        boolean b = eduCourseService.updateById(eduCourse);
        return b?R.ok():R.error();
    }
    //课程的分页查询
    @GetMapping("pageCourse/{current}/{pageCount}")
    public R pageCourse(@PathVariable long current,@PathVariable long pageCount){
        Page<EduCourse> pageCourse = new Page<>(current,pageCount);
        eduCourseService.page(pageCourse,null);
        long currentPage = pageCourse.getCurrent();
        List<EduCourse> eduCourse = pageCourse.getRecords();
        long total = pageCourse.getTotal();

        return R.ok().data("list",eduCourse).data("total",total).data("current",currentPage);
    }
    //课程条件分页查询

    //删除课程的信息 小节 章节 课程
    @DeleteMapping("deleteCourse/{id}")
    public R deleteCourse(@PathVariable String id){
        eduCourseService.deleteCourseService(id);
        return R.ok();
    }
    //订单的查询，远程调用，获取课程的信息
    @PostMapping("getCourse")
    public CourseOrderVo getCourse(String courseId){
//        CourseFrontVo courseInfo = eduCourseService.getCourseInfo(courseId);
        CourseFrontVo courseInfo = eduCourseService.getCourseInfo(courseId);

        CourseOrderVo courseOrderVo = new CourseOrderVo();
        BeanUtils.copyProperties(courseInfo,courseOrderVo);
        return courseOrderVo;
    }

}

