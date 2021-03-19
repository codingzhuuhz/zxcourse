package com.pengzhen.order.client;

import com.pengzhen.commonutils.ordervo.UserOrderVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name = "service-user")
public interface UserClient {
    @PostMapping("/userservice/user/getUserInfo/{id}")
    public UserOrderVo getUserInfo(@PathVariable("id") String id);
}
