package com.example.fsp.controller;

import com.example.fsp.bean.UserBean;
import com.example.fsp.context.UserContext;
import com.example.fsp.utils.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {
    @PostMapping("/login/jwt")
    public String login(@RequestBody UserBean user) {
        // 判断账号密码是否正确，这一步肯定是要读取数据库中的数据来进行校验的，这里为了模拟就省去了
        if ("admin".equals(user.getName()) && "123456".equals(user.getPassword())) {
            // 如果正确的话就返回生成的token（注意哦，这里服务端是没有存储任何东西的）
            return JwtUtil.generate(user.getName());
        }
        return "账号密码错误";
    }

    @GetMapping("api/jwt")
    public String api() {
        String currentUserName = UserContext.getCurrentUserName();
        System.out.println("Service层---当前用户登录名：" + currentUserName);
        return "api成功返回数据";
    }
}
