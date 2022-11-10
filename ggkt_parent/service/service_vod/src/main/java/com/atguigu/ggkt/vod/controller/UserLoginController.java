package com.atguigu.ggkt.vod.controller;

import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vod.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author...Z.Yao..
 * @create 2022-11-01-17:33
 * 用户登录Controller
 */
@RestController
@RequestMapping(value = "/admin/vod/user")
//@CrossOrigin("*")
public class UserLoginController {

    //Login   {"code":20000,"data":{"token":"admin-token"}}
    @PostMapping("login")
    public Result login() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", "admin-token");
        return Result.ok(map);
    }


    //info
    //{"code":20000,"data":{"roles":["admin"],
    // "introduction":"I am a super administrator",
    // "avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
    // "name":"Super Admin"}}
    @GetMapping("info")
    public Result info() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("roles","admin");
        map.put("introduction","I am a super administrator");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name","Super Admin");
        return Result.ok(map);
    }



}
