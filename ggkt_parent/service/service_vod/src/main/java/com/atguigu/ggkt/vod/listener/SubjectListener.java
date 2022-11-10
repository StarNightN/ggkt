package com.atguigu.ggkt.vod.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.vo.vod.SubjectEeVo;
import com.atguigu.ggkt.vod.mapper.SubjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author...Z.Yao..
 * @create 2022-11-02-21:39
 * excel导入数据监听类
 */
@Component
public class SubjectListener extends AnalysisEventListener<SubjectEeVo> {

    @Autowired
    private SubjectMapper subjectMapper;

    //一行一行读取，从第二行开始读取
    @Override
    public void invoke(SubjectEeVo subjectEeVo, AnalysisContext analysisContext) {
        Subject subject = new Subject();
        //subject ----->subjectEeVo
        BeanUtils.copyProperties(subjectEeVo,subject);
        //添加
        subjectMapper.insert(subject);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
