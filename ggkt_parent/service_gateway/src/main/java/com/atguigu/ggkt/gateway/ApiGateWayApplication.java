package com.atguigu.ggkt.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author...Z.Yao..
 * @create 2022-11-06-16:48
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateWayApplication.class,args);
    }
}
