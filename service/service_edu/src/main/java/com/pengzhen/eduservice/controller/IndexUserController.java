package com.pengzhen.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pengzhen.eduservice.entity.EduCourse;
import com.pengzhen.eduservice.entity.EduTeacher;
import com.pengzhen.eduservice.service.EduCourseService;
import com.pengzhen.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("eduservice/index")
public class IndexUserController {
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduTeacherService eduTeacherService;
    @Cacheable(key="'hotTeacher'",value="teachers")
    @GetMapping("indexTeacher")
    public List<EduTeacher> indexTeacher(){


        QueryWrapper<EduTeacher> wrapper2 = new QueryWrapper<>();
        //查询老师的前4条数据
        wrapper2.orderByDesc("id");
        wrapper2.last("limit 4");
        List<EduTeacher> teachers = eduTeacherService.list(wrapper2);

        return teachers;
    }
    @Cacheable(key = "'hotCourse'",value = "courses")
    @GetMapping("indexCourse")
    public List<EduCourse> indexCourse(){
        QueryWrapper<EduCourse> wrapper1 = new QueryWrapper<>();
        //查询课程的前8条数据
        wrapper1.orderByDesc("id");
        wrapper1.last("limit 8");
        List<EduCourse> courses = eduCourseService.list(wrapper1);

        return courses;
    }
}
