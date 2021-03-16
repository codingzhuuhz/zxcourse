package com.pengzhen.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pengzhen.commonutils.R;
import com.pengzhen.eduservice.entity.EduTeacher;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pengzhen
 * @since 2021-02-23
 */
public interface EduTeacherService extends IService<EduTeacher> {

    R pageTeacher(long current, long limit);
}
