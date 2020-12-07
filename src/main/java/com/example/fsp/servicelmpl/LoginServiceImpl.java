package com.example.fsp.servicelmpl;

import com.example.fsp.bean.*;
import com.example.fsp.mapper.master.LoginMapper;
import com.example.fsp.service.LoginService;
import com.example.fsp.utils.PageUtils;
import com.example.fsp.utils.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService, UserDetailsService {

    //    @Autowired
//    RedisTemplate redisTemplate;
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    private LoginMapper loginMapper;

//    @Override
//    public Result<List<UserBean>> getLogin(String name) {
////        if (redisUtil.hasKey("allUser")) {//存在
////            System.out.println("从缓存中获取" + redisUtil.get("allUser").toString());
////            return new Result<List<UserBean>>(JSON.parseArray(redisUtil.get("allUser").toString(), UserBean.class));
////        } else {
////            System.out.println("从数据库中获取" + loginMapper.getLogin(name));
////            redisUtil.set("allUser", JSON.toJSONString(loginMapper.getLogin(name)), Constants.CACHE_EXP_TEN_SECONDS);
////            return new Result<>(loginMapper.getLogin(name));
////        }
//        return new Result<>(loginMapper.getLogin(name));
//    }

    @Override
    public List<UserBean> getAll() {
        return loginMapper.getAll();
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }
    /**
     * 调用分页插件完成分页
     * @param
     * @return
     */
    private PageInfo<UserBean> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<UserBean> sysMenus = loginMapper.selectPage();
        return new PageInfo<UserBean>(sysMenus);
    }

    @Override
    public UserBean getUserById(Integer id) {
        return loginMapper.getUserById(id);
    }

    @Override
    public int delete(Integer id) {
        return loginMapper.delete(id);
    }

    @Override
    public int updateUser(UserBean userBean) {
        return loginMapper.updateUser(userBean);
    }

    @Override
    public int addUser(UserBean userBean) {
        return loginMapper.addUser(userBean);
    }

    @Override
    public List<UserBean> findUserByCondition(UserBean userBean) {
        return loginMapper.findUserByCondition(userBean);
    }

    @Override
    public List<UserBean> findUserInIds(QueryVo vo) {
        return loginMapper.findUserInIds(vo);
    }

    @Override
    public UserBean getInfoByLogin(UserBean userBean) {
        return loginMapper.getInfoByLogin(userBean);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 从数据库中查询出用户实体对象
        System.out.println("loadUserByUsername------------->>>");
        UserBean userBean = new UserBean();
        userBean.setName(s);
        UserBean user = loginMapper.findUserByCondition(userBean).get(0);
        // 若没查询到一定要抛出该异常，这样才能被Spring Security的错误处理器处理
        if (user == null) {
            throw new UsernameNotFoundException("没有找到该用户");
        }
        // 走到这代表查询到了实体对象，那就返回我们自定义的UserDetail对象（这里权限暂时放个空集合，后面我会讲解）
        return new UserDetail(user, Collections.emptyList());
    }
}
