package com.example.fsp.context;

import com.example.fsp.bean.UserBean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public final class RequestContext {
    private RequestContext(){

    }
    public static HttpServletRequest getCurrentRequest(){
        return ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
    }
    public static UserBean getCurrentUser(){
        return (UserBean) getCurrentRequest().getSession().getAttribute("user");
    }
}
