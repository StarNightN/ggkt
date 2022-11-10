package com.atguigu.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author...Z.Yao..
 * @create 2022-11-02-20:27
 */
@Data
public class User {

    @ExcelProperty(value = "用户编号",index = 0)
    private int id;

    @ExcelProperty(value = "用户名称",index = 1)
    private String name;
}
