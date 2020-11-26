package com.example.fsp.controller;

import com.example.fsp.bean.UserModel;
import com.example.fsp.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "用户账户接口")
@RequestMapping("/userca")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    //请求例子：http://localhost:9001/getUserWithCompany/1
    /*请求结果：{"id":1,"name":"aa","company":{"id":1,"companyName":"xx公司"},"accounts":null}*/
    @ApiOperation("用户所在公司")
    @RequestMapping(value = "/getUserWithCompany/{id}", method = RequestMethod.POST)
    public UserModel getUserWithCompany(@PathVariable("id") Long id) {
        UserModel user = userMapper.getUserWithCompany(id);
        return user;
    }


    //请求例子：http://localhost:9001/getUserWithAccount/1
    /*请求结果：{"id":1,"name":"aa","company":null,"accounts":[{"id":1,"accountName":"中行"},{"id":2,"accountName":"工行"}]}*/
    @ApiOperation("用户账户")
    @RequestMapping(value = "/getUserWithAccount/{id}", method = RequestMethod.POST)
    public UserModel getUserWithAccount(@PathVariable("id") Long id) {
        UserModel user = userMapper.getUserWithAccount(id);
        return user;
    }


    //请求例子：http://localhost:9001/getUserWithAccount/1
  /*请求结果：[{"id":1,"name":"aa","company":{"id":1,"companyName":"xx公司"},"accounts":[{"id":1,"accountName":"中行"},
    {"id":2,"accountName":"工行"}]},{"id":2,"name":"bb","company":{"id":2,"companyName":"yy公司"},"accounts":[{"id":3,"accountName":"中行"}]}]*/
    @ApiOperation("所有用户")
    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public List<UserModel> getUsers() {
        List<UserModel> users = userMapper.getAll();
        return users;
    }
}
