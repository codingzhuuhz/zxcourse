package com.pengzhen.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pengzhen.eduservice.entity.EduSubject;
import com.pengzhen.eduservice.entity.excel.ExcelHeaderData;
import com.pengzhen.eduservice.service.EduSubjectService;
import com.pengzhen.servicebase.exception.zxException;

import java.util.Date;

//@Component
public class ExcelListener extends AnalysisEventListener<ExcelHeaderData> {
//    @Autowired
    private EduSubjectService eduSubjectService;
    public ExcelListener(){}
    public ExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(ExcelHeaderData excelHeaderData, AnalysisContext analysisContext) {
        if(excelHeaderData == null){
            throw new zxException(20001,"所选文件为空!");
        }
//        这一部分有待进行代码优化
        QueryWrapper<EduSubject> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("title",excelHeaderData.getOneSubjectName());
        wrapper1.eq("parent_id","0");
        EduSubject one = eduSubjectService.getOne(wrapper1);
        String parent_id = "";
        if(one == null){
            one = new EduSubject();
            one.setTitle(excelHeaderData.getOneSubjectName());
            one.setParentId("0");
            one.setGmtCreate(new Date());
            one.setGmtModified(new Date());
            eduSubjectService.save(one);
            parent_id = eduSubjectService.getOne(wrapper1).getId();
        }else{
             parent_id = one.getId();
        }
        QueryWrapper<EduSubject> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("title",excelHeaderData.getTwoSubjectName());
        wrapper2.eq("parent_id",parent_id);

        EduSubject one1 = eduSubjectService.getOne(wrapper2);
        if(one1 == null){
            one1 = new EduSubject();
            one1.setTitle(excelHeaderData.getTwoSubjectName());
            one1.setParentId(parent_id);
            one1.setGmtCreate(new Date());
            one1.setGmtModified(new Date());
            eduSubjectService.save(one1);
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
