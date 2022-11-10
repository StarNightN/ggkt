package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Chapter;
import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vo.vod.ChapterVo;
import com.atguigu.ggkt.vod.service.ChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-11-03
 */
@RestController
@RequestMapping("/admin/vod/chapter")
@Api(tags = "章节控制器")
//@CrossOrigin("*")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    //1课程大纲列表（章节和小节列表）
    @ApiOperation("大纲课程列表")
    @GetMapping("getNestedTreeList/{courseId}")
    public Result getTreeList(@PathVariable Long courseId){
        List<ChapterVo> list = chapterService.getTreeList(courseId);
        return Result.ok(list);
    }

    //2.添加章节
    @ApiOperation("添加章节")
    @PostMapping("save")
    public Result save(@RequestBody Chapter chapter){
        chapterService.save(chapter);
        return Result.ok(null);
    }

    //3.修改-根据id查询
    @ApiOperation("修改前查询")
    @PostMapping("get/{id}")
    public Result save(@RequestBody Long id){
        Chapter chapter = chapterService.getById(id);
        return Result.ok(chapter);
    }


    //4.修改最终实现
    @ApiOperation("修改前查询")
    @PostMapping("update")
    public Result update(@RequestBody Chapter chapter){
        chapterService.updateById(chapter);
        return Result.ok(null);
    }

    //5.删除章节
    @ApiOperation("删除章节")
    @DeleteMapping("remove/{id}")
    public Result delete(@RequestBody Long id){
        chapterService.removeById(id);
        return Result.ok(null);
    }


}

