package com.pengzhen.statistic.task;

import com.pengzhen.statistic.service.StatisticsDailyService;
import com.pengzhen.statistic.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    @Autowired
    private StatisticsDailyService statisticsDailyService;
    //每天的凌晨12点进行更新数据
    //查询前一天的数据 然后更新到 statistic
    @Scheduled(cron = "0001/1*?")
    public void update(){
        String day = DateUtil.getBeforeDate();
        statisticsDailyService.generateStatDataService(day);
    }
}
