package com.atguigu.ggkt.vod.controller;

import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vod.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author...Z.Yao..
 * @create 2022-11-02-15:43
 * 文件上传
 */
@RestController
@Api(tags = "文件上传接口")
@RequestMapping("/admin/vod/file")
public class FileUploadController {

    @Autowired
    private FileService fileService;


    @ApiOperation("文件上传")
    @PostMapping("upload")
    public Result uploadFile(MultipartFile file){

        String url = fileService.upload(file);
        return Result.ok(url).message("上传文件成功");
    }

}
