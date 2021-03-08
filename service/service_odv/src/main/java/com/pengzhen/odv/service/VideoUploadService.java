package com.pengzhen.odv.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoUploadService {
    String uploadVideoService(MultipartFile file);

    void deleteVideoService(String videoId);

    void deleteBatchVideoService(List<String> videoIds);
}
