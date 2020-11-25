package com.example.fsp.servicelmpl;

import com.example.fsp.bean.UserBean;
import com.example.fsp.mapper.UserMapper;
import com.example.fsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserBean loginIn(String name, String password) {
        return userMapper.getInfo(name, password);
    }

    @Override
    public String addUser(UserBean user) {
        // 直接编写业务逻辑
        //执行写入
        int row = jdbcTemplate.update("INSERT INTO user (id,name,password)VALUES (?,?,?);", user.getId(), user.getName(), user.getPassword());
        //返回结果
        return "success" + row;
    }
}
