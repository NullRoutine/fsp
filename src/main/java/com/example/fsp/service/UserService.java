package com.example.fsp.service;

import com.example.fsp.bean.UserBean;

public interface UserService {
    /**
     *
     * @param user 用户对象
     * @return 成功则返回"success"，失败则返回错误信息
     */
    String addUser(UserBean user);
}
