package com.atguigu.ggkt.client.user;

import com.atguigu.ggkt.model.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author...Z.Yao..
 * @create 2022-11-06-22:39
 */
@FeignClient(value = "service-user")
public interface UserInfoFeignClient {

    @GetMapping("inner/getById/{id}")
    public UserInfo getById(@PathVariable Long id);
}
