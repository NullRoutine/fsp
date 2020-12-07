package com.example.fsp.controller;

import com.example.fsp.bean.*;
import com.example.fsp.config.APIException;
import com.example.fsp.service.LoginService;
import com.example.fsp.service.UserService;
import com.example.fsp.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "用户接口")
@RequestMapping("user")
public class LoginController {

    //将Service注入Web层
    @Autowired
    UserService userService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtManager;

    //    @RequestMapping("/login")
//    public String show() {
//        return "login";
//    }
    @ApiOperation("用户登录")
    @RequestMapping(value = "/loginIn", method = RequestMethod.POST)
    public String login(@RequestBody @Valid UserBean userBean) {
        System.out.println(userBean.toString());
        UserBean userBeanTemp = loginService.getInfoByLogin(userBean);
        // 若没有查到用户或者密码校验失败则抛出异常
        if (userBeanTemp == null || !passwordEncoder.matches(userBean.getPassword(), userBeanTemp.getPassword())) {
            throw new APIException(ResultCode.AUTH_ERROR, "账号密码错误");
        }

//        Authentication token = new UsernamePasswordAuthenticationToken(userBean.getName(), userBean.getPassword());
//        // AuthenticationManager校验这个认证信息，返回一个已认证的Authentication
//        Authentication authentication = authenticationManager.authenticate(token);
//        // 将返回的Authentication存到上下文中
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 需要返回给前端的VO对象
        userBeanTemp.setToken(jwtManager.generate(userBeanTemp.getName()));
        if (userBeanTemp != null) {
            return "success" + userBeanTemp.toString();
        } else {
            return "error";
        }
    }

    @ApiOperation("添加用户")
    @PostMapping("/addUser")
    public Result addUser(@RequestBody @Valid UserBean userBean) {
        // 将用户实体对象添加到数据库
        UserBean userBean1 = new UserBean();
        userBean1.setName(userBean.getName());
        userBean1.setPassword(passwordEncoder.encode(userBean.getPassword()));
        loginService.addUser(userBean1);
        int id = userBean1.getId();
        return new Result<>(id);
    }

    @ApiOperation("获取所有用户")
    @GetMapping("/selectAllUser")
    public Result<List<UserBean>> getAll() {
//        System.out.println(name);
//        System.out.println(loginService.getLogin(name));
        return new Result<>(loginService.getAll());
    }

    @ApiOperation("获得单个用户")
    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public Result<UserBean> getUserById(@RequestParam int id) {
        return new Result<>(loginService.getUserById(id));
    }

    @ApiOperation("删除用户")
    @DeleteMapping("deleteUser")
    public Result<String> delete(int id) {
        int i = loginService.delete(id);
        return new Result<>("删除成功" + i);
    }

    @ApiOperation("更新用户")
    @PostMapping("/updateUser")
    public Result<String> updateUser(@RequestBody @Valid UserBean userBean) {
        int id = loginService.updateUser(userBean);
        return new Result<>("更新成功" + id);
    }

    @ApiOperation("根据条件查用户")
    @PostMapping("/getUserByCondition")
    public Result<List<UserBean>> findUserByCondition(@RequestBody UserBean userBean) {
        return new Result<>(loginService.findUserByCondition(userBean));
    }

    @ApiOperation("根据id数组查用户")
    @PostMapping("/findUserInIds")
    public Result<List<UserBean>> findUserInIds(@RequestBody QueryVo vo) {
        QueryVo queryVo = new QueryVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(101);
        list.add(104);
        queryVo.setIds(list);
        return new Result<>(loginService.findUserInIds(queryVo));
    }

    /**
     * 分页查询
     *
     * @param pageQuery
     * @return
     */
    @ApiOperation("分页查询")
    @PostMapping(value = "/findPage")
    public Object findPage(@RequestBody PageRequest pageQuery) {
        return loginService.findPage(pageQuery);
    }
}
