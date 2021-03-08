package com.pengzhen.eduservice.service;

import com.pengzhen.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author pengzhen
 * @since 2021-02-26
 */
public interface EduSubjectService extends IService<EduSubject> {

    void uploadExcelService(MultipartFile file,EduSubjectService eduSubjectService);
}
