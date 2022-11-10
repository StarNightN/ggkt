package com.atguigu.ggkt.vod.service.impl;

import com.atguigu.ggkt.model.vod.Chapter;
import com.atguigu.ggkt.model.vod.Video;
import com.atguigu.ggkt.vo.vod.ChapterVo;
import com.atguigu.ggkt.vo.vod.VideoVo;
import com.atguigu.ggkt.vod.mapper.ChapterMapper;
import com.atguigu.ggkt.vod.service.ChapterService;
import com.atguigu.ggkt.vod.service.VideoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-03
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;

    @Override
    public List<ChapterVo> getTreeList(Long courseId) {
        //定义最终数据list集合
        ArrayList<ChapterVo> finalChapterList = new ArrayList<>();
        //根据课程id获取课程所有章节
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<Chapter> chapterList = baseMapper.selectList(wrapper);
        //更具课程id获取课程里面所有小节
        LambdaQueryWrapper<Video> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Video::getCourseId,courseId);
        List<Video> videoList = videoService.list(lambdaQueryWrapper);
        //封装章节
        for (Chapter chapter:chapterList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            finalChapterList.add(chapterVo);
            //封装章节里面的小节
            ArrayList<VideoVo> videoVoArrayList = new ArrayList<>();
            for (Video video:videoList) {
                VideoVo videoVo = new VideoVo();
                if(chapter.getId().equals(video.getChapterId())){
                    BeanUtils.copyProperties(video,videoVo);
                    videoVoArrayList.add(videoVo);
                }
            }
            //把章节里面所有小节集合放到每个章节中
            chapterVo.setChildren(videoVoArrayList);
        }
        return finalChapterList;
    }

    //根据课程id删除章节
    @Override
    public void removeCourseById(Long id) {
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        baseMapper.delete(wrapper);
    }
}
