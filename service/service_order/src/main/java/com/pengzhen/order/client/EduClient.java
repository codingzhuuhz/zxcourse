package com.pengzhen.order.client;

import com.pengzhen.commonutils.ordervo.CourseOrderVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient("service_edu")
public interface EduClient {
    @PostMapping("/eduservice/course/getCourse")
    public CourseOrderVo getCourse(String courseId);
}
