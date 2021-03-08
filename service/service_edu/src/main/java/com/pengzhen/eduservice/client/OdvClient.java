package com.pengzhen.eduservice.client;

import com.pengzhen.commonutils.R;
import com.pengzhen.eduservice.client.impl.OdvClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="service-odv",fallback = OdvClientImpl.class)
@Component
public interface OdvClient {
    @DeleteMapping("/odvservice/upload/deleteVideo/{videoId}")
    public R deleteVideo(@PathVariable("videoId") String videoId);
    //@RequestParam：将请求参数绑定到你控制器的方法参数上（是springmvc中接收普通参数的注解） 但是   可以不写
    @DeleteMapping("/odvservice/upload/deleteVideo")
    public R deleteBatchVideo(@RequestParam("videoIds") List<String> videoIds);
}
