package com.pengzhen.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pengzhen.commonutils.R;
import com.pengzhen.eduservice.entity.EduSubject;
import com.pengzhen.eduservice.entity.SubjectDataForm;
import com.pengzhen.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author pengzhen
 * @since 2021-02-26
 */
@RestController
@RequestMapping("/eduservice/edusubject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;
    @PostMapping("uploadExcel")
    public void uploadExcel(MultipartFile file){
        eduSubjectService.uploadExcelService(file,eduSubjectService);

    }
    @GetMapping("findAllSubject")
    public R findAllSubject(){
        QueryWrapper<EduSubject> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("parent_id","0");
        //查出学科的所有数据
        List<EduSubject> list = eduSubjectService.list(wrapper1);
        //使用iterator来遍历查询出来的所有数据
//        Iterator<EduSubject> iterator = list.iterator();
        //构造一个数据集合
        List<SubjectDataForm> res = new ArrayList<>();
        //遍历学科的所有数据
        for(EduSubject item :list){


                SubjectDataForm sdf = new SubjectDataForm();
                //为0的第一树枝的 id title 组合下
                sdf.setId(item.getId());
                sdf.setLabel(item.getTitle());
                //利用0的id 来查询一组子数据
                QueryWrapper<EduSubject>  wrapper= new QueryWrapper<>();
                wrapper.eq("parent_id", item.getId());
                List<EduSubject> twoRes = eduSubjectService.list(wrapper);
                //构造子数据的数据集合
                List<SubjectDataForm> children = new ArrayList<>();
                //遍历子数据
                for(EduSubject twoRe:twoRes){
                    SubjectDataForm sdf2 = new SubjectDataForm();
                    sdf2.setId(twoRe.getId());
                    sdf2.setLabel(twoRe.getTitle());
                    children.add(sdf2);
                }
                sdf.setChildren(children);
                res.add(sdf);
            }
        return R.ok().data("list",res);
    }
}

