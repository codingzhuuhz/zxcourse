package com.pengzhen.statistic.client;

import com.pengzhen.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Component
@FeignClient(name = "service-user")
public interface StatisticClient {
    @GetMapping("/userservice/user/getUserStatistic/{day}")
    public R getUserStatistic(@PathVariable("day") String day);
}
