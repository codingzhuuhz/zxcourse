package com.pengzhen.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pengzhen.commonutils.R;
import com.pengzhen.eduservice.entity.EduCourse;
import com.pengzhen.eduservice.entity.EduTeacher;
import com.pengzhen.eduservice.service.EduCourseService;
import com.pengzhen.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("eduservice/index")
public class IndexTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;
    @Autowired
    private EduCourseService eduCourseService;
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageTeacher(@PathVariable long current,@PathVariable long limit){
        //Page<EduTeacher> pageTeacher = new Page<>(page,limit);
        R result = eduTeacherService.pageTeacher(current, limit);
        return result;
    }
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable long id){
        EduTeacher teacher = eduTeacherService.getById(id);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",id);
        List<EduCourse> courseList = eduCourseService.list(wrapper);
        return R.ok().data("teacher",teacher).data("courseList",courseList);
    }
}
