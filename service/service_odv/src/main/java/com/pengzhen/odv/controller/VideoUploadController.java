package com.pengzhen.odv.controller;

import com.pengzhen.commonutils.R;
import com.pengzhen.odv.service.VideoUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/odvservice/upload")
public class VideoUploadController {
    @Autowired
    private VideoUploadService videoUploadService;
    @PostMapping("uploadVideo")
    public R uploadVideo(MultipartFile file){
        String s = videoUploadService.uploadVideoService(file);
        return R.ok().data("videoSourceId",s);
    }
    @DeleteMapping("deleteVideo/{videoId}")
    public R deleteVideo(@PathVariable String videoId){
        videoUploadService.deleteVideoService(videoId);
        return R.ok();
    }
    @DeleteMapping("deleteBatchVideo")
    public R deleteBatchVideo(@RequestParam("videoIds") List<String> videoIds){
        videoUploadService.deleteBatchVideoService(videoIds);
        return R.ok();
    }
}
