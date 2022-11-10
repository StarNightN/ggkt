package com.atguigu.ggkt.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author...Z.Yao..
 * @create 2022-11-02-15:44
 */
public interface FileService {
    //文件上传
    String upload(MultipartFile file);
}
