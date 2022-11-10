package com.atguigu.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author...Z.Yao..
 * @create 2022-11-02-20:39
 * Excel读数据监听器
 */
public class ExcelListtener extends AnalysisEventListener<User> {
    @Override
    //一行一行读取excel内容，把每一行内容封装到user对象中
    //从excel第二行开始读取
    public void invoke(User user, AnalysisContext analysisContext) {
        System.out.println(user);
    }

    //读取表头
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头："+headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


}
