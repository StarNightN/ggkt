package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vod.service.VideoService;
import com.atguigu.ggkt.vod.service.VideoVisitorService;
import io.swagger.annotations.Api;
import org.bouncycastle.crypto.StagedAgreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-11-05
 */
@RestController
@RequestMapping("/admin/vod/videoVisitor")
//@CrossOrigin("*")
@Api(tags = "统计接口控制器")
public class VideoVisitorController {

    @Autowired
    private VideoVisitorService videoVisitorService;

    //课程统计的接口
    @ApiIgnore("课程统计的接口")
    @GetMapping("findCount/{courseId}/{startDate}/{endDate}")
    public Result findCount(@PathVariable Long courseId,
                            @PathVariable String startDate,
                            @PathVariable String endDate){
        Map<String ,Object> map = videoVisitorService.findCount(courseId,startDate,endDate);
        return Result.ok(map);
    }

}

