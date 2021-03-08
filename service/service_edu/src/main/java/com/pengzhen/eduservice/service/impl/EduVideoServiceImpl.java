package com.pengzhen.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengzhen.commonutils.R;
import com.pengzhen.eduservice.client.OdvClient;
import com.pengzhen.eduservice.entity.EduVideo;
import com.pengzhen.eduservice.mapper.EduVideoMapper;
import com.pengzhen.eduservice.service.EduVideoService;
import com.pengzhen.servicebase.exception.zxException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author pengzhen
 * @since 2021-02-26
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    @Autowired
    private OdvClient odvClient;
//刪除課程的小节中的视频 和 小节中内容
    @Override
    public void removeVideoService(String courseId) {
        //删除课程时候删除aliyun中的视频
        QueryWrapper<EduVideo> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("course_id",courseId);

        wrapper1.select("video_source_id");//这个仅仅查询 video_source_id这个值

        List<EduVideo> eduVideos = this.baseMapper.selectList(wrapper1);

//        这一种方法 用到了 更多的知识点
//        List<String> videoIds = new ArrayList<>();
//        for (EduVideo eduVideo:eduVideos) {
//            String cid = eduVideo.getCourseId();
//            if(!StringUtils.isEmpty(cid)){
//                videoIds.add(cid);
//            }
//        }
//        if(videoIds!=null){
//            odvClient.deleteBatchVideo(videoIds);
//        }
        for(EduVideo eduVideo:eduVideos){
            String videoId = eduVideo.getVideoSourceId();
            if(!StringUtils.isEmpty(videoId)){
                R res = odvClient.deleteVideo(videoId);
                if(res.getCode() == 20001){
                    throw  new zxException(20001,"服务器没响应，熔断器执行了");
                }
            }
        }
        QueryWrapper<EduVideo> wrapper2 = new QueryWrapper();
        wrapper2.eq("course_id",courseId);
        this.baseMapper.delete(wrapper2);
    }
}
