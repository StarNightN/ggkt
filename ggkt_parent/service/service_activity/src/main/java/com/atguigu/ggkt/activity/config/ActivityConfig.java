package com.atguigu.ggkt.activity.config;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author...Z.Yao..
 * @create 2022-11-06-21:46
 */
@Configuration
@MapperScan("com.atguigu.ggkt.activity.mapper")
public class ActivityConfig {

    //添加分页插件
    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor(){
        return new PaginationInnerInterceptor();
    }

}
