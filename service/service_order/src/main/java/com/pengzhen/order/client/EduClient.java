package com.pengzhen.order.client;

import com.pengzhen.commonutils.ordervo.CourseOrderVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name = "service-edu")
public interface EduClient {
    @PostMapping("/eduservice/course/getCourse/{id}")
    public CourseOrderVo getCourse(@PathVariable("id") String courseId);
}
