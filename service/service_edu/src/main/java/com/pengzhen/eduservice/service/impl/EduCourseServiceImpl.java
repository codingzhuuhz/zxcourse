package com.pengzhen.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengzhen.commonutils.R;
import com.pengzhen.eduservice.entity.EduCourse;
import com.pengzhen.eduservice.entity.EduCourseDescription;
import com.pengzhen.eduservice.entity.vo.CourseInfo;
import com.pengzhen.eduservice.entity.vo.CoursePublishVo;
import com.pengzhen.eduservice.entity.vo.frontVo.CourseFrontVo;
import com.pengzhen.eduservice.mapper.EduCourseMapper;
import com.pengzhen.eduservice.service.EduChapterService;
import com.pengzhen.eduservice.service.EduCourseDescriptionService;
import com.pengzhen.eduservice.service.EduCourseService;
import com.pengzhen.eduservice.service.EduVideoService;
import com.pengzhen.servicebase.exception.zxException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author pengzhen
 * @since 2021-02-26
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService ;
    @Autowired
    private EduChapterService eduChapterService;
    @Autowired
    private EduVideoService eduVideoService;
    @Override
    public String addCourseInfoService(CourseInfo courseInfo) {
        EduCourse eduCourse = new EduCourse();
        //?
//        BeanUtils.copyProperties(courseInfo,eduCourse);
        eduCourse.setPrice(courseInfo.getPrice());
        eduCourse.setLessonNum(courseInfo.getLessonNum());
        eduCourse.setTitle(courseInfo.getTitle());

        eduCourse.setGmtCreate(new Date());
        eduCourse.setGmtModified(new Date());
        int insert = this.baseMapper.insert(eduCourse);
        if(insert<0){
            new zxException(20001,"课程添加失败");
        }
        //这里使用baseMapper.insert(eduCourse)插入成功之后，可以获取返回 内容
        String courseId = eduCourse.getId();
//        System.out.println(eduCourse.getTitle());
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfo.getDescription());
        eduCourseDescription.setId(courseId);
        eduCourseDescription.setGmtCreate(new Date());
        eduCourseDescription.setGmtModified(new Date());
        eduCourseDescriptionService.save(eduCourseDescription) ;
        return courseId;
    }

    @Override
    public R updateCourseInfo(CourseInfo courseInfo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        eduCourse.setGmtCreate(new Date());
        eduCourse.setGmtModified(new Date());
        this.baseMapper.updateById(eduCourse);

        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfo,eduCourseDescription);
        eduCourseDescription.setGmtCreate(new Date());
        eduCourseDescription.setGmtModified(new Date());

        boolean b = eduCourseDescriptionService.updateById(eduCourseDescription);

        return b?R.ok():R.error();
    }

    @Override
    public CourseInfo getCourseInfoByIdService(String id) {

        CourseInfo courseInfo = new CourseInfo();
        EduCourse eduCourse = this.baseMapper.selectById(id);
        BeanUtils.copyProperties(eduCourse,courseInfo);
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(id);
        BeanUtils.copyProperties(eduCourseDescription,courseInfo);

        return courseInfo;
    }

    @Override
    public CoursePublishVo getCoursePublishService(String courseId) {

        return this.baseMapper.showCoursePublish(courseId);
    }

    @Override
    public void deleteCourseService(String id) {
        //删除小节


        eduVideoService.removeVideoService(id);
        //删除章节

        eduChapterService.removeChapterService(id);
        //删除课程描述

        eduCourseDescriptionService.removeCourseDescriptionService(id);
        //删除课程
        int i = this.baseMapper.deleteById(id);
        if(i==0){
            throw  new zxException(20001,"课程删除失败");
        }
    }
//前台课程分页显示
    @Override
    public R pageCourse(long current, long limit) {
        Page<EduCourse> pageCourse = new Page<>(current,limit);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        this.baseMapper.selectPage(pageCourse,wrapper);
        long current1 = pageCourse.getCurrent();
        List<EduCourse> records = pageCourse.getRecords();
        long pages = pageCourse.getPages();
        long total = pageCourse.getTotal();
        boolean next = pageCourse.hasNext();
        boolean previous = pageCourse.hasPrevious();
        Map<String, Object> map = new HashMap<>();

        map.put("total",total);
        map.put("pages",pages);
        map.put("current",current1);
        map.put("next",next);
        map.put("previous",previous);
        map.put("courses",records);
        return R.ok().data(map);
    }
//前台课程信息显示
    @Override
    public CourseFrontVo getCourseInfo(String id) {
        return this.baseMapper.getCourseFrontInfo(id);
    }
}
