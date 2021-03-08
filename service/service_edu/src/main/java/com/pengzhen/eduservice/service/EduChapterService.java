package com.pengzhen.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pengzhen.eduservice.entity.EduChapter;
import com.pengzhen.eduservice.entity.EduVideo;
import com.pengzhen.eduservice.entity.vo.chapter.Chapter;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author pengzhen
 * @since 2021-02-26
 */
public interface EduChapterService extends IService<EduChapter> {

    List<Chapter> getChapterLittleChapterService(String courseId);

    boolean deleteVideoService(String id);

    EduVideo getVideoService(String id);

    boolean saveVideoService(EduVideo eduVideo);

    boolean updateVideoService(EduVideo eduVideo);

    void removeChapterService(String id);
}
