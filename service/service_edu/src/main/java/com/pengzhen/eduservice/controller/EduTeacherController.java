package com.pengzhen.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pengzhen.commonutils.R;
import com.pengzhen.eduservice.entity.EduTeacher;
import com.pengzhen.eduservice.entity.vo.TeacherQuery;
import com.pengzhen.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pengzhen
 * @since 2021-02-23
 */
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService ;
    @GetMapping("findAllTeacher")
    public R findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items",list);
    }

    @DeleteMapping("deleteTeacher/{id}")
    public R deleteTeacher(@PathVariable String id){
        boolean res = eduTeacherService.removeById(id) ;
        if(res){
            return R.ok() ;
        }else{
            return R.error();
        }
    }
    //讲师的分页查询
    @GetMapping("pageTeacher/{current}/{pageCount}")
    public R pageTeacher(@PathVariable long current,@PathVariable long pageCount){
        Page<EduTeacher> pageTeacher = new Page<>(current,pageCount);

        eduTeacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal();
        List<EduTeacher> list = pageTeacher.getRecords();
        return R.ok().data("total",total).data("list",list);
    }
    //条件查询带分页
    @GetMapping("pageTeacherCondition/{current}/{pageCount}")
    public R pageTeacherCondition(@PathVariable long current,@PathVariable long pageCount,
                                   @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> pageTeacher = new Page<>(current,pageCount);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        if(teacherQuery!=null){
            String name = teacherQuery.getName();
            Integer level = teacherQuery.getLevel();
            String gmtCreate1 = teacherQuery.getGmtCreate1();
            String gmtCreate2 = teacherQuery.getGmtCreate2();
            if(!StringUtils.isEmpty(name)){
                wrapper.like("name",name);
            }
            if(!StringUtils.isEmpty(level)){
                wrapper.equals(level) ;
            }
            if(!StringUtils.isEmpty(gmtCreate1)){
                wrapper.ge("gmtCreate",gmtCreate1);
            }
            if(!StringUtils.isEmpty(gmtCreate2)){
                wrapper.le("gmtCreate",gmtCreate2);
            }
        }

        //调用方法根据条件查询出来的值 存放在pageTeacher中
        eduTeacherService.page(pageTeacher,wrapper) ;
        long total = pageTeacher.getTotal();
        List<EduTeacher> list = pageTeacher.getRecords();
        return R.ok().data("total",total).data("list",list);
    }
    @PostMapping("increaseTeacher")
    public R insertTeacher(@RequestBody EduTeacher eduTeacher){
        boolean res = eduTeacherService.save(eduTeacher);
        //测试自定义异常 自定义异常 需要自己抛出去
//        try{
//            int i = 10/0 ;
//        }catch (Exception e){
//            throw new zxException(100,"指向自定义异常处理器");
//        }
        return res?R.ok():R.error();
    }
    @GetMapping("findTeacherById/{id}")
    public R findTeacher(@PathVariable long id){
        EduTeacher eduTeacher = eduTeacherService.getById(id) ;
        return R.ok().data("teacher",eduTeacher);
    }
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        //调用方法直接将Teacher对象传入其中
        boolean res = eduTeacherService.updateById(eduTeacher) ;
        return res?R.ok():R.error();
    }
}

