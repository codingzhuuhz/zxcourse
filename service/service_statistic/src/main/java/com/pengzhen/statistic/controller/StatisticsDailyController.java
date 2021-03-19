package com.pengzhen.statistic.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pengzhen.commonutils.R;
import com.pengzhen.statistic.entity.StatisticsDaily;
import com.pengzhen.statistic.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author pengzhen
 * @since 2021-03-18
 */
@RestController
@CrossOrigin
@RequestMapping("/statistic/statistics_daily")
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService statisticsDailyService;
    //生成统计分析的数据 天内的注册的人数
    @GetMapping("generateStatisticData/{day}")
    public R generateStatisticData(@PathVariable String day){
        statisticsDailyService.generateStatDataService(day);

        return R.ok();
    }
    //查询统计表得到每天的登录数，
    @GetMapping("getStatisticDailyData/{day}")
    public R getStatisticDailyData(@PathVariable String day){
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        StatisticsDaily daily = statisticsDailyService.getOne(wrapper);
        return R.ok().data("daily",daily);
    }

}

