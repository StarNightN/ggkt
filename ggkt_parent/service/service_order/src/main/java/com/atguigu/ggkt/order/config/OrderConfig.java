package com.atguigu.ggkt.order.config;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author...Z.Yao..
 * @create 2022-11-06-17:45
 * serviceOrder配置类
 */
@Configuration
@MapperScan("com.atguigu.ggkt.order.mapper")
public class OrderConfig {

    //注册分页插件
    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor(){
        return new PaginationInnerInterceptor();
    }
}
