package com.atguigu.ggkt.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author...Z.Yao..
 * @create 2022-11-05-20:26
 */
public interface VodService {
    String uploadVideo();

    void removeVideo(String fileId);
}
