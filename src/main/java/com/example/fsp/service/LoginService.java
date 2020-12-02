package com.example.fsp.service;

import com.example.fsp.bean.QueryVo;
import com.example.fsp.bean.UserBean;

import java.util.List;

public interface LoginService {
//    Result<List<UserBean>> getLogin(String name);

    /**
     * 获取所有
     *
     * @return
     */
    List<UserBean> getAll();

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    UserBean getUserById(Integer id);

    /**
     * 删除
     *
     * @param id
     */
    int delete(Integer id);

    /**
     * 更新
     *
     * @param userBean
     */
    int updateUser(UserBean userBean);

    /**
     * 新增
     *
     * @param userBean
     */
    int addUser(UserBean userBean);

    List<UserBean> findUserByCondition(UserBean userBean);

    List<UserBean> findUserInIds(QueryVo vo);

    UserBean getInfoByLogin(UserBean userBean);

}
