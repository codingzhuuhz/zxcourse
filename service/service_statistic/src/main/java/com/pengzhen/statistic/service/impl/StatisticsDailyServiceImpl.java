package com.pengzhen.statistic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pengzhen.commonutils.R;
import com.pengzhen.statistic.client.StatisticClient;
import com.pengzhen.statistic.entity.StatisticsDaily;
import com.pengzhen.statistic.mapper.StatisticsDailyMapper;
import com.pengzhen.statistic.service.StatisticsDailyService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author pengzhen
 * @since 2021-03-18
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
    @Autowired
    private StatisticClient statisticClient;
    @Override
    public void generateStatDataService(String day) {
        /**
         * 1.根据day查询出来数据库中是否存在一条记录，如果存在查询出来进行更新，或者是保存进去
         * 2.每次查询 先将之前的一条数据给删除掉 将最新的数据给插入进去
         */
        //将之前的statistics_daily统计表中的数据
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        this.baseMapper.delete(wrapper);

        R userStatistic = statisticClient.getUserStatistic(day);
        //每天的注册的数量
        Integer count = (Integer) userStatistic.getData().get("count");
        //每天的在线人数统计

        //每天的视频播放数量

        //每天的新增的课程数量
        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setRegisterNum(count);
        statisticsDaily.setLoginNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setCourseNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setVideoViewNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setDateCalculated(day);
        statisticsDaily.setGmtCreate(new Date());
        statisticsDaily.setGmtModified(new Date());

        this.baseMapper.insert(statisticsDaily);
    }
}
