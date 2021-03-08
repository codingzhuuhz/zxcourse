package com.pengzhen.ossservice.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.pengzhen.ossservice.service.ossService;
import com.pengzhen.ossservice.utils.constantOssUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Calendar;
import java.util.UUID;
@Service
public class ossServiceImpl implements ossService {
    @Override
    public String uploadAvatarService(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = constantOssUtils.ENDPOINT ;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = constantOssUtils.KEYID;
        String accessKeySecret = constantOssUtils.KEYSECRENT;
        String backetName = constantOssUtils.BUCKETNAME;
        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            String fileName = file.getOriginalFilename();
            fileName = UUID.randomUUID().toString().replace("-","")+fileName;
            Calendar instance = Calendar.getInstance();
            int year = instance.get(Calendar.YEAR);
            int month = instance.get(Calendar.MONTH)+1;
            int day = instance.get(Calendar.DATE);
            fileName = year+"/"+month+"/"+day+"/"+fileName;

            // 上传文件流。
//            InputStream inputStream = new FileInputStream(fileName);
            InputStream inputStream = file.getInputStream();
            ossClient.putObject(backetName, fileName, inputStream);
            String url = "http://"+backetName+"."+endpoint+"/"+fileName;
            // 关闭OSSClient。
            ossClient.shutdown();
            return url ;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
