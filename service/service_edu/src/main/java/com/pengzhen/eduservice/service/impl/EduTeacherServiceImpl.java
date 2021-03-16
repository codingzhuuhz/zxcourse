package com.pengzhen.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengzhen.commonutils.R;
import com.pengzhen.eduservice.entity.EduTeacher;
import com.pengzhen.eduservice.mapper.EduTeacherMapper;
import com.pengzhen.eduservice.service.EduTeacherService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pengzhen
 * @since 2021-02-23
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public R pageTeacher(long current, long limit) {
        Page<EduTeacher> teacherPage = new Page<>(current,limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        this.baseMapper.selectPage(teacherPage,wrapper);
        long current1 = teacherPage.getCurrent();
        List<EduTeacher> records = teacherPage.getRecords();
        long pages = teacherPage.getPages();
        long total = teacherPage.getTotal();
        boolean next = teacherPage.hasNext();
        boolean previous = teacherPage.hasPrevious();
        Map<String, Object> map = new HashMap<>();
        map.put("pages",pages);
        map.put("total",total);
        map.put("current",current1);
        map.put("next",next);
        map.put("previous",previous);
        map.put("teachers",records);
        return R.ok().data(map);
    }
}
