package com.atguigu.ggkt.vod.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.ggkt.exeption.GgktException;
import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.vo.vod.SubjectEeVo;
import com.atguigu.ggkt.vod.listener.SubjectListener;
import com.atguigu.ggkt.vod.mapper.SubjectMapper;
import com.atguigu.ggkt.vod.service.SubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-02
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Autowired
    private SubjectListener subjectListener;

    //查询父节点相同的记录
    @Override
    public List<Subject> selectSubjectList(Long id) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        List<Subject> subjectLists = baseMapper.selectList(wrapper);
        //遍历subjectlist，得到每个subject对象，判断是否有下一层数据，有haschilden  = true
        for (Subject subject:subjectLists) {
            //获取subjectid的值
            Long subjectId = subject.getId();
            //查询
            boolean ischild = this.isChilden(subjectId);
            //封装到对象里面
            subject.setHasChildren(ischild);
        }
        return subjectLists;
    }

    //判断是否有下层数据，以自己的id作为父id查询，看是否自己有孩子
    private boolean isChilden(Long subjectId) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",subjectId);
        Integer count = baseMapper.selectCount(wrapper);
        return count > 0;

    }

    //课程分类导出
    @Override
    public void exportData(HttpServletResponse response) {
        //设置下载信息
        response.setContentType("application/vnd.ms-excel");
        String fileName = null;
        try {
            //URLEncoder.encode 可以防止中文乱码，当然和easyexcel 没有关系
            fileName = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-disposition","attachment;filename="+fileName+".xlsx");
            //查询课程分类表所有数据
            List<Subject> subjectList = baseMapper.selectList(null);

            //list<Subject>----->list<SubjectEeVo>
            ArrayList<SubjectEeVo> subjectEeVoList = new ArrayList<>();
            for (Subject subject:subjectList){
                SubjectEeVo subjectEeVo = new SubjectEeVo();
//                subjectEeVo.setId(subject.getId());
//                subjectEeVo.setParentId(subject.getParentId());
                BeanUtils.copyProperties(subject,subjectEeVo);
                subjectEeVoList.add(subjectEeVo);
            }

            //easyExcel写操作
            EasyExcel.write(response.getOutputStream(), SubjectEeVo.class).sheet("课程分类").doWrite(subjectEeVoList);
        } catch (Exception e) {
            throw new GgktException(20001,"导出失败");
        }

    }

    //课程分类导入
    @Override
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),SubjectEeVo.class,subjectListener).sheet().doRead();
        } catch (IOException e) {
            throw new GgktException(20001,"导入失败");
        }
    }


}
