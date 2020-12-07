package com.example.fsp.mapper.master;

import com.example.fsp.bean.QueryVo;
import com.example.fsp.bean.UserBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LoginMapper {
    @Select("SELECT * FROM user")
    List<UserBean> getLogin(@Param("name") String name);

    UserBean getInfoByLogin(UserBean userBean);

    /**
     * 获取所有
     *
     * @return
     */
    List<UserBean> getAll();

    /**
     * 分页查询
     */
    List<UserBean> selectPage();

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

    /**
     * 根据条件查询
     *
     * @param userBean
     * @return
     */
    List<UserBean> findUserByCondition(UserBean userBean);

    /**
     * @param vo
     * @return
     */
    List<UserBean> findUserInIds(QueryVo vo);
}
