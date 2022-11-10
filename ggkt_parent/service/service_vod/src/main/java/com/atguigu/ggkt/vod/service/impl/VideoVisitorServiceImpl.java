package com.atguigu.ggkt.vod.service.impl;

import com.atguigu.ggkt.model.vod.VideoVisitor;
import com.atguigu.ggkt.vo.vod.VideoVisitorCountVo;
import com.atguigu.ggkt.vod.mapper.VideoVisitorMapper;
import com.atguigu.ggkt.vod.service.VideoVisitorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Handler;
import java.util.stream.Collectors;

/**
 * <p>
 * 视频来访者记录表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-05
 */
@Service
public class VideoVisitorServiceImpl extends ServiceImpl<VideoVisitorMapper, VideoVisitor> implements VideoVisitorService {

    @Override
    public Map<String ,Object> findCount(Long courseId, String startDate, String endDate) {
        //调用mapper的方法
        List<VideoVisitorCountVo> videoVisitorCountVoList = baseMapper.findCount(courseId,startDate,endDate);
        //创建map集合
        HashMap<String, Object> map = new HashMap<>();
        //创建两个list集合，一个代表所有日期，一个代表日期对应数量
        List<String> dateList = videoVisitorCountVoList.stream().map(VideoVisitorCountVo::getJoinTime).
                collect(Collectors.toList());
        List<Integer> countList = videoVisitorCountVoList.stream().map(VideoVisitorCountVo::getUserCount).
                collect(Collectors.toList());

        //放大map中
        map.put("xDate",dateList);
        map.put("yDate",countList);
        return map;
    }
}
