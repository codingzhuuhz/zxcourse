package com.pengzhen.odv.utils;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class constantOdvUtils implements InitializingBean {

    @Value("${aliyun.file.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.file.accessKeySecret}")
    private String accessKeySecret;

    public static String ACCESSKEYID;
    public static String ACCESSKEYSECRET;

    //constrantOssUtils交给spring管理之后 指向@Value 由于实现initBean 进而直接执行aferPropertiesSet
    @Override
    public void afterPropertiesSet() throws Exception {
        constantOdvUtils.ACCESSKEYID = this.accessKeyId ;
        constantOdvUtils.ACCESSKEYSECRET = this.accessKeySecret;
    }

    //文件一流的方式进行上传
    public static  String uploadUploadStream(String accessKeyId, String accessKeySecret, String title, String fileName, InputStream inputStream){
        UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName, inputStream);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            return response.getVideoId();
        } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            return null;
        }
    }

    public static void deleteUploadVideoFile(String accessKeyId,String  accessKeySecret,String videoId) throws Exception{
        DefaultAcsClient client = initVodClient(accessKeyId, accessKeySecret);
        DeleteVideoRequest request = new DeleteVideoRequest();
        DeleteVideoResponse response = new DeleteVideoResponse();
        request.setVideoIds(videoId);
        response = client.getAcsResponse(request);
    }
    public static void deleteBatchVideoFile(String accessKeyId, String  accessKeySecret, List<String> videoIds)throws Exception{
        DefaultAcsClient client = initVodClient(accessKeyId, accessKeySecret);
        DeleteVideoRequest request = new DeleteVideoRequest();
        DeleteVideoResponse response = new DeleteVideoResponse();
        String ids = String.join(",", videoIds);
        request.setVideoIds(ids);
        response = client.getAcsResponse(request);
    }
    //文件上传需要传入文件的绝对的路径
    public static void uploadOwnVideoFile(String accessKeyId,String  accessKeySecret,String title,String fileName){
    //        String accessKeyId = "LTAI4GBCEnQ1AWBZ87ehv15e";
    //        String accessKeySecret = "DrdPIDzOSkLHeEJWHehXhMqmvAUR6Y";
    //        String title = "test上传视频";
    //        String fileName = "D:\\在线课程项目\\bb59d3a7f15e7f2c2e718564b2315bb2.mp4";
        //实例化一个request
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        //实例化一个上传组件
        UploadVideoImpl uploader = new UploadVideoImpl();
        //开始上传
        UploadVideoResponse response = uploader.uploadVideo(request);

        if (response.isSuccess()) {
            System.out.print("VideoId="+response.getVideoId()+"\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId="+response.getVideoId()+"\n");
            System.out.print("ErrorCode="+response.getCode()+"\n");
            System.out.print("ErrorMessage="+response.getMessage()+"\n");
        }
    }
    //获取视频播放的凭证
    public static void getUploadFileAuth() throws Exception{

        DefaultAcsClient client = initVodClient("LTAI4GBCEnQ1AWBZ87ehv15e", "DrdPIDzOSkLHeEJWHehXhMqmvAUR6Y");
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        request.setVideoId("b4a99bfcf303412582e3cdb13df6dcf6");
        response = client.getAcsResponse(request);
        System.out.print("UploadAuth = " + response.getPlayAuth() + "\n");
    }
    //获取aliyun中上传视频的信息
    public static void getUploadFileInfo(){
        try{
            //1.实列化一个客户端
            DefaultAcsClient client =  initVodClient("LTAI4GBCEnQ1AWBZ87ehv15e", "DrdPIDzOSkLHeEJWHehXhMqmvAUR6Y");
            //实例化一个request
            GetVideoInfosRequest request = new GetVideoInfosRequest();
            //实例化一个response
            GetVideoInfosResponse response = new GetVideoInfosResponse();
            //设置文件的id
            request.setVideoIds("b4a99bfcf303412582e3cdb13df6dcf6");
            //获取客户端响应的内容
            response = client.getAcsResponse(request);
            if (response.getVideoList() != null && response.getVideoList().size() > 0) {
                System.out.print("===== exist video infos : ===== \n");
                for (GetVideoInfosResponse.Video video : response.getVideoList()) {
                    System.out.print("VideoId = " + video.getVideoId() + "\n");
                    System.out.print("Title = " + video.getTitle() + "\n");

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //初始化点播客户端
    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws Exception {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}

