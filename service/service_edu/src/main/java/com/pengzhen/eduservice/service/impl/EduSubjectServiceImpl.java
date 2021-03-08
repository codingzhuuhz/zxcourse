package com.pengzhen.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengzhen.eduservice.entity.EduSubject;
import com.pengzhen.eduservice.entity.excel.ExcelHeaderData;
import com.pengzhen.eduservice.listener.ExcelListener;
import com.pengzhen.eduservice.mapper.EduSubjectMapper;
import com.pengzhen.eduservice.service.EduSubjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author pengzhen
 * @since 2021-02-26
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void uploadExcelService(MultipartFile file,EduSubjectService eduSubjectService) {
//        EasyExcel..write(file.getInputStream(), ExcelHeaderData.class,new ExcelListener()).sheet().doWrite();
        try{
            EasyExcel.read(file.getInputStream(), ExcelHeaderData.class, new ExcelListener(eduSubjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
