package com.pengzhen.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengzhen.eduservice.entity.EduCourseDescription;
import com.pengzhen.eduservice.mapper.EduCourseDescriptionMapper;
import com.pengzhen.eduservice.service.EduCourseDescriptionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author pengzhen
 * @since 2021-02-26
 */
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {

    @Override
    public void removeCourseDescriptionService(String id) {
        QueryWrapper<EduCourseDescription> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        this.baseMapper.delete(wrapper);
    }
}
