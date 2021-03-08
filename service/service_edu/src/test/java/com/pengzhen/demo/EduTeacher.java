package com.pengzhen.demo;

import com.pengzhen.eduservice.service.EduTeacherService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class EduTeacher {
    @Autowired
    private EduTeacherService eduTeacherService;
    @Test
    public void insertTeacher(){
        com.pengzhen.eduservice.entity.EduTeacher teacher = new com.pengzhen.eduservice.entity.EduTeacher() ;
        teacher.setName("pray");
        teacher.setCareer("获得过国家一级讲师称号");
        teacher.setIsDeleted(0);
        teacher.setGmtCreate(new Date());
        teacher.setGmtModified(new Date());
        eduTeacherService.save(teacher);
    }
}
