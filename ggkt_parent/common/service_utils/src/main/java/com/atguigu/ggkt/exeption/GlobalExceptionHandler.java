package com.atguigu.ggkt.exeption;

import com.atguigu.ggkt.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author...Z.Yao..
 * @create 2022-10-30-16:46
 * 全局异常处理和特定异常处理
 */
@ControllerAdvice //aop
public class GlobalExceptionHandler {

    //全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail(null).message("执行全局异常处理");
    }

    //特定异常处理ArithmeticException
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result ArithmeticException(ArithmeticException e){
        e.printStackTrace();
        return Result.fail(null).message("执行特定异常处理ArithmeticException");
    }

    //自定义异常处理 GgktException
    @ExceptionHandler(GgktException.class)
    @ResponseBody
    public Result GgktException(GgktException e){
        e.printStackTrace();
        return Result.fail(null).code(e.getCode()).message(e.getMsg());
    }



}
