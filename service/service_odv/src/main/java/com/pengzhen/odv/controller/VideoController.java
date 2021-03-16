package com.pengzhen.odv.controller;

import com.pengzhen.commonutils.R;
import com.pengzhen.odv.utils.constantOdvUtils;
import com.pengzhen.servicebase.exception.zxException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/odvservice/video")
public class VideoController {
    @GetMapping("getPlayAuth/{videoSourceId}")
    public  R getPlayAuth(@PathVariable String videoSourceId){
        String uploadFileAuth = null;
        try {
            uploadFileAuth = constantOdvUtils.getUploadFileAuth(videoSourceId);
        } catch (Exception e) {
            throw new zxException(20001,"视频加载失败");
        }
        return R.ok().data("playAuth",uploadFileAuth);
    }
}
