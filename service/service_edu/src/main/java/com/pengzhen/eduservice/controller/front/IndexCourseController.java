package com.pengzhen.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pengzhen.commonutils.R;
import com.pengzhen.eduservice.entity.EduChapter;
import com.pengzhen.eduservice.entity.EduVideo;
import com.pengzhen.eduservice.entity.vo.chapter.Chapter;
import com.pengzhen.eduservice.entity.vo.chapter.LittleChapter;
import com.pengzhen.eduservice.entity.vo.frontVo.CourseFrontVo;
import com.pengzhen.eduservice.service.EduChapterService;
import com.pengzhen.eduservice.service.EduCourseService;
import com.pengzhen.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("eduservice/index")
public class IndexCourseController {
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;
    @Autowired
    private EduCourseService eduCourseService;
    @GetMapping("pageCourse/{current}/{limit}")
    public R pageCourse(@PathVariable long current,@PathVariable long limit){
       R result = eduCourseService.pageCourse(current,limit);
       return result;
    }
    /**
     *这个前台课程章节 小节     有待提升
     */
    @GetMapping("getCourse/{courseId}")
    public R getCourse(@PathVariable String courseId){

        CourseFrontVo course = eduCourseService.getCourseInfo(courseId);

        //通过课程id查询章节的信息
        QueryWrapper<EduChapter> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("course_id",courseId);
        List<EduChapter> chapters = eduChapterService.list(wrapper1);
        //制造一个章节
        ArrayList<Chapter> chapterList = new ArrayList<>();
        //遍历章节
        for(EduChapter chapter1:chapters){
            Chapter chapter = new Chapter();
            BeanUtils.copyProperties(chapter1,chapter);
            //将章节放入list
            chapterList.add(chapter);
            //操作小节
            //制造一个小节的数组
            ArrayList<LittleChapter> list = new ArrayList<>();
            //通过章节的id,查询小节的信息
            QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
            wrapper.eq("chapter_id",chapter1.getId());
            List<EduVideo> videos = eduVideoService.list(wrapper);
           //遍历小节信息
            for(EduVideo video:videos){
                //制造一个小节的对象
                LittleChapter littleChapter = new LittleChapter();
                BeanUtils.copyProperties(video,littleChapter);
                //将小节信息放到小节的集合
                list.add(littleChapter);
                //将小节的集合放到集合中的每一个章节中
                chapter.setLittleChapters(list);
            }
        }
        return R.ok().data("course",course).data("chapterList",chapterList);
    }

}
