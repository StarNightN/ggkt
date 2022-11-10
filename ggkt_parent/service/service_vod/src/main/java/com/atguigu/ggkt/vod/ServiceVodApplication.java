package com.atguigu.ggkt.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author...Z.Yao..
 * @create 2022-10-29-20:38
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.atguigu") //包扫描，扫描Swagger2Config配置类
@EnableDiscoveryClient//开启服务注册中心
public class ServiceVodApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceVodApplication.class,args);
    }
}
