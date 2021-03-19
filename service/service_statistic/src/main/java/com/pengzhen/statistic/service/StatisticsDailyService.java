package com.pengzhen.statistic.service;

import com.pengzhen.statistic.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author pengzhen
 * @since 2021-03-18
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void generateStatDataService(String day);
}
