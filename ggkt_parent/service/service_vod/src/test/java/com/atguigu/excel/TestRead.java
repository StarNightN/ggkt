package com.atguigu.excel;

import com.alibaba.excel.EasyExcel;

/**
 * @author...Z.Yao..
 * @create 2022-11-02-20:43
 */
public class TestRead {

    public static void main(String[] args) {
        //设置文件名称和路径
        String fileName = "D:\\atguigu.xlsx";
        //调用方法进行读取操作
        EasyExcel.read(fileName,User.class,new ExcelListtener()).sheet().doRead();
    }
}
