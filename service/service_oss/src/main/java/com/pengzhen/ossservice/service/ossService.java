package com.pengzhen.ossservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface ossService {
    String uploadAvatarService(MultipartFile file);
}
