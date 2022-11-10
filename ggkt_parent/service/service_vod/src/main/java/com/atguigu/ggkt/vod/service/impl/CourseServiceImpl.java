package com.atguigu.ggkt.vod.service.impl;

import com.atguigu.ggkt.model.vod.Course;
import com.atguigu.ggkt.model.vod.CourseDescription;
import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.vo.vod.CourseFormVo;
import com.atguigu.ggkt.vo.vod.CoursePublishVo;
import com.atguigu.ggkt.vo.vod.CourseQueryVo;
import com.atguigu.ggkt.vod.mapper.CourseMapper;
import com.atguigu.ggkt.vod.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-03
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    TeacherService teacherService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    CourseDescriptionService descriptionService;

    @Autowired
    ChapterService chapterService;

    @Autowired
    VideoService videoService;

    @Override
    //点播课程列表
    public Map<String, Object> findPageCourse(Page<Course> coursePage, CourseQueryVo courseQueryVo) {
        //获取条件
        String title = courseQueryVo.getTitle();
        Long subjectId = courseQueryVo.getSubjectId();//二层分类
        Long subjectParentId = courseQueryVo.getSubjectParentId();//一层分类
        Long teacherId = courseQueryVo.getTeacherId();
        //判断条件为空，封装条件
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(subjectId)){
            wrapper.like("subjectId",subjectId);
        }
        if(!StringUtils.isEmpty(subjectParentId)){
            wrapper.like("subjectParentId",subjectParentId);
        }
        if(!StringUtils.isEmpty(teacherId)){
            wrapper.like("teacherId",teacherId);
        }
        //调用方法实现条件查询分页
        Page<Course> pages = baseMapper.selectPage(coursePage, wrapper);
        List<Course> list = pages.getRecords();
        long totalPage = pages.getPages();
        long totalCount = pages.getTotal();

        //查询数据里面有几个id
        //讲师id，课程分类id
        //获取这些id对应的名称，进行封装，最终显示
        list.stream().forEach(item ->{
            this.getNameById(item);
        });
        //封装数据
        HashMap<String, Object> map = new HashMap<>();
        map.put("totalCount",totalCount);
        map.put("totalPage",totalPage);
        map.put("records",list);
        return map;
    }



    //获取这些id对应的名称，进行封装，最终显示
    private Course getNameById(Course course) {
        Teacher teacher = teacherService.getById(course.getTeacherId());
        if(teacher != null){
            course.getParam().put("teacherName",teacher.getName());
        }
        Subject subjectTwo = subjectService.getById(course.getSubjectId());
        if(subjectTwo != null){
            course.getParam().put("subjectTitle",subjectTwo.getTitle());
        }
        Subject subjectOne = subjectService.getById(course.getSubjectParentId());
        if(subjectOne != null){
            course.getParam().put("subjectParentTitle",subjectOne.getTitle());
        }
        return course;
    }



    //添加课程
    @Override
    public Long saveCourseInfo(CourseFormVo courseFormVo) {
        //添加课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo,course);
        baseMapper.insert(course);
        //添加课程描述信息，操作course_description
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseFormVo.getDescription());
        courseDescription.setCourseId(course.getId());
        descriptionService.save(courseDescription);
        return course.getId();
    }

    //根据id获取课程
    @Override
    public CourseFormVo getCourseInfoById(Long id) {
        //课程基本信息
        Course course = baseMapper.selectById(id);
        //课程描述信息
        CourseDescription courseDescription = new CourseDescription();
        QueryWrapper<CourseDescription> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        CourseDescription description = descriptionService.getOne(wrapper);
        //封装基本信息
        if(course == null){
            return null;
        }
        CourseFormVo courseFormVo = new CourseFormVo();
        BeanUtils.copyProperties(course,courseFormVo);
        //封装描述信息
        if(description != null){
            courseFormVo.setDescription(description.getDescription());
        }
        return courseFormVo;
    }

    //修改课程信息
    @Override
    public void updateCourseId(CourseFormVo courseFormVo) {
        //修改课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo,course);
        baseMapper.updateById(course);
        //修改描述信息
        CourseDescription description = new CourseDescription();
        description.setDescription(courseFormVo.getDescription());
//        QueryWrapper<CourseDescription> wrapper = new QueryWrapper<>();
//        wrapper.eq("course_id",course.getId());
//        descriptionService.update(description,wrapper);
        //设置课程描述id
        description.setId(course.getId());
        descriptionService.updateById(description);
    }

    //获取发布确认信息
    @Override
    public CoursePublishVo getCoursePublishVo(Long id) {
        return baseMapper.selectCoursePublishVoById(id);
    }

    //课程最终发布
    @Override
    public void publishCourse(Long id) {
        Course course = baseMapper.selectById(id);
        course.setStatus(1);//已经发布
        course.setPublishTime(new Date());
        baseMapper.updateById(course);
    }

    //删除课程
    @Override
    public void removeCourseId(Long id) {
        //1.根据id删除小节
        videoService.removeVideoByCourseId(id);
        //2.根据课程id删除章节
        chapterService.removeCourseById(id);
        //3.根据课程id删除课程描述
        descriptionService.removeById(id);
        //4.根据课程id删除课程
        baseMapper.deleteById(id);

    }
}
