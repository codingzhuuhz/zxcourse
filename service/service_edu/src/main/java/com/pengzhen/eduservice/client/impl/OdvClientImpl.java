package com.pengzhen.eduservice.client.impl;

import com.pengzhen.commonutils.R;
import com.pengzhen.eduservice.client.OdvClient;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class OdvClientImpl implements OdvClient {
    @Override
    public R deleteVideo(String videoId) {
        return R.error().message("删除视频出错");
    }

    @Override
    public R deleteBatchVideo(List<String> videoIds) {
        return R.error().message("多个视频删除失败");
    }
}
