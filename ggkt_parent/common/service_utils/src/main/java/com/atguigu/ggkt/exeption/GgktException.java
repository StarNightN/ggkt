package com.atguigu.ggkt.exeption;

import com.atguigu.ggkt.result.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author...Z.Yao..
 * @create 2022-10-30-16:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GgktException extends RuntimeException{

    private Integer code;
    private String msg;

}
