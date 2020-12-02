package com.example.fsp.config;

import com.alibaba.fastjson.JSON;
import com.example.fsp.bean.Result;
import com.example.fsp.bean.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class MyEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        Result result = new Result<>(ResultCode.AUTH_ERROR, "认证错误");
        // 直接提示前端认证错误
        out.write(JSON.toJSONString(result));
        out.flush();
        out.close();
    }
}
