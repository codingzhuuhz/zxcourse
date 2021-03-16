package com.pengzhen.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengzhen.commonutils.R;
import com.pengzhen.eduservice.client.OdvClient;
import com.pengzhen.eduservice.entity.EduChapter;
import com.pengzhen.eduservice.entity.EduVideo;
import com.pengzhen.eduservice.entity.vo.chapter.Chapter;
import com.pengzhen.eduservice.entity.vo.chapter.LittleChapter;
import com.pengzhen.eduservice.mapper.EduChapterMapper;
import com.pengzhen.eduservice.service.EduChapterService;
import com.pengzhen.eduservice.service.EduVideoService;
import com.pengzhen.servicebase.exception.zxException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author pengzhen
 * @since 2021-02-26
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private OdvClient odvClient;
    @Autowired
    private EduVideoService eduVideoService;

    /**
     * 后台章节小节的显示，有待提高
     * @param courseId
     * @return
     */
    @Override
    public List<Chapter> getChapterLittleChapterService(String courseId) {
        QueryWrapper<EduChapter> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("course_id",courseId);
        //查询章节
        List<EduChapter> eduChapters = this.baseMapper.selectList(wrapper1);
        QueryWrapper<EduVideo> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("course_id",courseId);
        List<EduVideo> list = eduVideoService.list(wrapper2);

        List<Chapter> chapter = new ArrayList<>();

        for(EduChapter eduChapter:eduChapters){
            Chapter chapter1 = new Chapter();
            chapter1.setId(eduChapter.getId());
            chapter1.setTitle(eduChapter.getTitle());
//            chapter.add(chapter1);
            //添加小结
            List<LittleChapter> littleChapter =  new ArrayList<>();
            for(EduVideo eduVideo:list){
                if(eduVideo.getCourseId().equals(eduChapter.getCourseId())){
                    LittleChapter littleChapter1 = new LittleChapter();
                    littleChapter1.setId(eduVideo.getId());
                    littleChapter1.setTitle(eduVideo.getTitle());
                    littleChapter.add(littleChapter1);
                }
            }
            chapter1.setLittleChapters(littleChapter);
            chapter.add(chapter1);
        }
        return chapter;
    }

    @Override
    public boolean deleteVideoService(String id) {
        //删除小节中 删除aliyun中的视频
        EduVideo eduVideo = eduVideoService.getById(id);

        String videoId = eduVideo.getVideoSourceId();

        if(!StringUtils.isEmpty(videoId)){
            R r = odvClient.deleteVideo(videoId);
            if(r.getCode() == 20001){
                throw new zxException(20001,"熔断器");

            }
        }

        return eduVideoService.removeById(id);
    }

    @Override
    public EduVideo getVideoService(String id) {
        return eduVideoService.getById(id);
    }

    @Override
    public boolean saveVideoService(EduVideo eduVideo) {
        return eduVideoService.save(eduVideo);
    }

    @Override
    public boolean updateVideoService(EduVideo eduVideo) {
        return eduVideoService.updateById(eduVideo);
    }

    @Override
    public void removeChapterService(String id) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        this.baseMapper.delete(wrapper);
    }
}
