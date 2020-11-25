package com.example.fsp.mapper;

import com.example.fsp.bean.UserBean;

public interface UserMapper {
    UserBean getInfo(String name,String password);
}
