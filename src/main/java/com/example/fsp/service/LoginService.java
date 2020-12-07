package com.example.fsp.service;

import com.example.fsp.bean.PageRequest;
import com.example.fsp.bean.PageResult;
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
     * 分页查询接口
     * 这里统一封装了分页请求和结果，避免直接引入具体框架的分页对象, 如MyBatis或JPA的分页对象
     * 从而避免因为替换ORM框架而导致服务层、控制层的分页接口也需要变动的情况，替换ORM框架也不会
     * 影响服务层以上的分页接口，起到了解耦的作用
     *
     * @param pageRequest 自定义，统一分页查询请求
     * @return PageResult 自定义，统一分页查询结果
     */
    PageResult findPage(PageRequest pageRequest);

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
