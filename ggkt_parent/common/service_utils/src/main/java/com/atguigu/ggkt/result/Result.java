package com.atguigu.ggkt.result;

import lombok.Data;

/**
 * @author...Z.Yao..
 * @create 2022-10-30-14:37
 */
//统一返回结果类
@Data
public class Result<T> {
    //状态吗
    private Integer code;
    //返回状态信息（成功 失败）
    private String message;
    //最终返回数据
    private T data;

    public Result(){
    }

//    //成功的方法,没有data数据
//    public static<T> Result<T> ok(){
//        Result<T> result = new Result<>();
//        result.setCode(200);
//        result.setMessage("成功");
//        return result;
//    }
//
//    //失败的方法，没有data数据
//    public static<T> Result<T> fail(){
//        Result<T> result = new Result<>();
//        result.setCode(201);
//        result.setMessage("失败");
//        return result;
//    }

    //成功的方法,有data数据
    public static<T> Result<T> ok(T data){
        Result<T> result = new Result<>();
        result.setCode(20000);
        result.setMessage("成功");
        if(data != null){
            result.setData(data);
        }
        return result;
    }

    //失败的方法，有data数据
    public static<T> Result<T> fail(T data){
        Result<T> result = new Result<>();
        result.setCode(20001);
        result.setMessage("失败");
        if(data != null){
            result.setData(data);
        }
        return result;
    }

    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }


}
