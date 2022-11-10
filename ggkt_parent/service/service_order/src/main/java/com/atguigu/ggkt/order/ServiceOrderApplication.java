package com.atguigu.ggkt.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author...Z.Yao..
 * @create 2022-11-06-17:40
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class,args);
    }
}
