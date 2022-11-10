package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Video;
import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vod.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-11-03
 */
@RestController
@RequestMapping("/admin/vod/video")
//@CrossOrigin("*")
@Api(tags = "小节控制器")
public class VideoController {

    @Autowired
    private VideoService videoService;

    //1.查询小节
    @ApiOperation("查询小节")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id){
        Video video = videoService.getById(id);
        return Result.ok(video);
    }

    //2.添加小节
    @ApiOperation("添加小节")
    @PostMapping("save")
    public Result save(@RequestBody Video video){
        videoService.save(video);
        return Result.ok(null);
    }

    //3.修改小节
    @ApiOperation("修改小节")
    @PutMapping("update")
    public Result updataById(@RequestBody Video video){
        videoService.updateById(video);
        return Result.ok(null);
    }


    //4.删除小节
    @ApiOperation("删除小节")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id){
        videoService.removeVideoById(id);
        return Result.ok(null);
    }


}

