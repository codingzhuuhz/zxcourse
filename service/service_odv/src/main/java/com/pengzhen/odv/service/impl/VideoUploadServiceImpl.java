package com.pengzhen.odv.service.impl;

import com.pengzhen.odv.service.VideoUploadService;
import com.pengzhen.odv.utils.constantOdvUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class VideoUploadServiceImpl implements VideoUploadService {

    @Override
    public String uploadVideoService(MultipartFile file) {

        String filename = file.getOriginalFilename();
        String title = "测试";
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return constantOdvUtils.uploadUploadStream(constantOdvUtils.ACCESSKEYID,constantOdvUtils.ACCESSKEYSECRET,title,filename,inputStream);
        }
    }

    @Override
    public void deleteVideoService(String videoId) {
        try {
            constantOdvUtils.deleteUploadVideoFile(constantOdvUtils.ACCESSKEYID,constantOdvUtils.ACCESSKEYSECRET,videoId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBatchVideoService(List<String> videoIds) {
        try {
            constantOdvUtils.deleteBatchVideoFile(constantOdvUtils.ACCESSKEYID,constantOdvUtils.ACCESSKEYSECRET,videoIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
