package com.atguigu.excel;

import com.alibaba.excel.EasyExcel;
import org.apache.velocity.util.ArrayListWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author...Z.Yao..
 * @create 2022-11-02-20:29
 */
public class TestWrite {

    public static void main(String[] args) {
        //设置文件名称和路径
        String fileName = "D:\\atguigu.xlsx";
        //调用方法
        EasyExcel.write(fileName,User.class).sheet("写操作").doWrite(data());
    }

    //循环遍历要设置添加的数据，最终封装到list集合中
    private static List<User> data(){
        List<User> list = new ArrayList<>();
        for (int i = 0;i < 10 ;i++) {
            User data = new User();
            data.setId(i);
            data.setName("张三"+i);
            list.add(data);
        }
        return list;
    }
}
