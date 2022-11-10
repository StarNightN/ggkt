package com.atguigu.ggkt.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author...Z.Yao..
 * @create 2022-11-02-15:33
 * 工具类读取配置文件信息
 */
@Component
public class ConstantPropertiesUtilOne implements InitializingBean {

    @Value("ap-nanjing")
    private String region;

    @Value("AKIDvy3uWzGI8LL8k9OUTIuHQJyPbva3OYUB")
    private String secretid;

    @Value("7EF6lCSUHrEPNFyPgjaFjyDI9mDRSUN4")
    private String secretkey;

    @Value("ggkt-2022-1314781302")
    private String bucketname;


    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = region;
        ACCESS_KEY_ID = secretid;
        ACCESS_KEY_SECRET = secretkey;
        BUCKET_NAME = bucketname;

    }
}
