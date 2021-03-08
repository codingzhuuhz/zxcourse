package com.pengzhen.eduservice.controller;


import com.pengzhen.commonutils.R;
import com.pengzhen.eduservice.entity.EduChapter;
import com.pengzhen.eduservice.entity.EduVideo;
import com.pengzhen.eduservice.entity.vo.chapter.Chapter;
import com.pengzhen.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;
    @GetMapping("getChapterLittleChapter/{courseId}")
    public R getChapterLittleChapter(@PathVariable String courseId){
        List<Chapter> list = eduChapterService.getChapterLittleChapterService(courseId);
        return R.ok().data("list",list);
    }
    @GetMapping("getChapter/{id}")
    public R getChapter(@PathVariable String id){
        EduChapter chapter = eduChapterService.getById(id);
        return R.ok().data("chapter",chapter);
    }
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        eduChapter.setGmtCreate(new Date());
        eduChapter.setGmtModified(new Date());
        boolean save = eduChapterService.save(eduChapter);
        return save?R.ok():R.error();
    }
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter){
        boolean b = eduChapterService.updateById(eduChapter);

        return b?R.ok():R.error();
    }
    @DeleteMapping("deleteChapter/{id}")
    public R deleteChapter(@PathVariable String id){
        boolean b = eduChapterService.removeById(id);
        return b?R.ok():R.error();
    }
//  ----------------小节操做------------------
    @GetMapping("getVideo/{id}")
    public R getVideo(@PathVariable String id){
        EduVideo eduVideo = eduChapterService.getVideoService(id);
        return R.ok().data("eduVideo",eduVideo);
    }
    @DeleteMapping("deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id){
        boolean b = eduChapterService.deleteVideoService(id);
        return b?R.ok():R.error();
    }
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        eduVideo.setGmtCreate(new Date());
        eduVideo.setGmtModified(new Date());
        boolean b = eduChapterService.saveVideoService(eduVideo);
        return b?R.ok():R.error();
    }
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        boolean b = eduChapterService.updateVideoService(eduVideo);
        return b?R.ok():R.error();
    }


}

