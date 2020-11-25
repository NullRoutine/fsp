package com.example.fsp.controller;

import com.example.fsp.bean.QueryVo;
import com.example.fsp.bean.Result;
import com.example.fsp.bean.UserBean;
import com.example.fsp.service.LoginService;
import com.example.fsp.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @RequestMapping("/login")
//    public String show() {
//        return "login";
//    }

//    @RequestMapping(value = "/loginIn", method = RequestMethod.POST)
//    public String login(String name, String password) {
//        UserBean userBean = userService.loginIn(name, password);
//        if (userBean != null) {
//            return "success";
//        } else {
//            return "error";
//        }
//    }

    @ApiOperation("添加用户")
    @PostMapping("/addUser")
    public Result addUser(@RequestBody @Valid UserBean userBean) {
        loginService.addUser(userBean);
        int id = userBean.getId();
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
}
