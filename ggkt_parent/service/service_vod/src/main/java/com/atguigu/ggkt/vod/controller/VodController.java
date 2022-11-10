package com.atguigu.ggkt.vod.controller;

import com.atguigu.ggkt.exeption.GgktException;
import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vod.service.VodService;
import com.atguigu.ggkt.vod.utils.ConstantPropertiesUtilOne;
import com.atguigu.ggkt.vod.utils.Signature;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;


/**
 * @author...Z.Yao..
 * @create 2022-11-05-20:24
 * 腾讯云视频上传下载
 */
@Api(tags = "腾讯云点播")
@RestController
@RequestMapping("/admin/vod")
//@CrossOrigin("*")
public class VodController {

    @Autowired
    private VodService vodService;

    //服务端上传视频接口
    @PostMapping("upload")
    @ApiOperation("上传视频")
    public Result upload(){
        String fileId = vodService.uploadVideo();
        return Result.ok(fileId);
    }

    //删除腾讯云视频
    @DeleteMapping("remove/{fileId}")
    public Result remove(@PathVariable String fileId){
        vodService.removeVideo(fileId);
        return Result.ok(null);
    }

    //返回客户端上传视频签名
    @GetMapping("sign")
    public Result sign(){
        Signature sign = new Signature();
         //设置 App 的云 API 密钥
        sign.setSecretId(ConstantPropertiesUtilOne.ACCESS_KEY_ID);
        sign.setSecretKey(ConstantPropertiesUtilOne.ACCESS_KEY_SECRET);
        sign.setCurrentTime(System.currentTimeMillis() / 1000);
        sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
        sign.setSignValidDuration(3600 * 24 * 2); // 签名有效期：2天
        try {
            String signature = sign.getUploadSignature();
            System.out.println("signature : " + signature);
            return Result.ok(signature);
        } catch (Exception e) {
            System.out.print("获取签名失败");
            e.printStackTrace();
            throw new GgktException(20001,"签名失败");
        }
    }

}
