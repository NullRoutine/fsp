package com.example.fsp.filter;

import com.example.fsp.servicelmpl.LoginServiceImpl;
import com.example.fsp.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    private LoginServiceImpl loginService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        if ("/login/one".equals(httpServletRequest.getRequestURI())) {
//            filterChain.doFilter(httpServletRequest, httpServletResponse);
//            return;
//        }
//        //已经登录
//        UserBean userBean = (UserBean) httpServletRequest.getSession().getAttribute("user");
//        if (userBean != null) {
//            filterChain.doFilter(httpServletRequest, httpServletResponse);
//            return;
//        }
//        // 走到这里就代表是其他接口，且没有登录
//        // 设置响应数据类型为json（前后端分离）
//        httpServletResponse.setContentType("application/json;charset=UTF-8");
//        PrintWriter out = httpServletResponse.getWriter();
//        // 设置响应内容，结束请求
//        out.write("请先登录");
//        out.flush();
//        out.close();
        Claims claims = jwtUtil.parse(httpServletRequest.getHeader("Authorization"));
        if (claims != null) {
            String username = claims.getSubject();
            // 查询出用户对象
            UserDetails user = loginService.loadUserByUsername(username);
            // 手动组装一个认证对象
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
            // 将认证对象放到上下文中
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
