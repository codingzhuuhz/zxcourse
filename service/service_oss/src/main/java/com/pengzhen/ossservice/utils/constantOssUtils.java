package com.pengzhen.ossservice.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class constantOssUtils implements InitializingBean {
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.file.keyid}")
    private String keyId;
    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;
    public static String ENDPOINT;
    public static String KEYID;
    public static String KEYSECRENT;
    public static String BUCKETNAME;
//constrantOssUtils交给spring管理之后 指向@Value 由于实现initBean 进而直接执行aferPropertiesSet
    @Override
    public void afterPropertiesSet() throws Exception {
        constantOssUtils.ENDPOINT = this.endpoint ;
        constantOssUtils.KEYID = this.keyId;
        constantOssUtils.KEYSECRENT = this.keySecret;
        constantOssUtils.BUCKETNAME = this.bucketName;

    }
}
