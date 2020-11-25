package com.example.fsp.servicelmpl;

import com.example.fsp.bean.QueryVo;
import com.example.fsp.bean.UserBean;
import com.example.fsp.mapper.LoginMapper;
import com.example.fsp.service.LoginService;
import com.example.fsp.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

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
}
