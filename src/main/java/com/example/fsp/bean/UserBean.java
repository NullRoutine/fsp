package com.example.fsp.bean;

import com.example.fsp.config.ExceptionCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel("用户")
public class UserBean {
    @ApiModelProperty("用户id")
    private Integer id;
    @ApiModelProperty("用户名")
    @NotNull(message = "用户名不能为空")
    @Size(min = 2, max = 11, message = "账号长度必须是6-11个字符")
    @ExceptionCode(value = 100001,message = "账号验证错误")
    private String name;
    @ApiModelProperty("用户密码")
    @NotNull(message = "密码不能为空")
    @Size(min = 3, max = 11, message = "密码长度必须是6-16个字符")
    @ExceptionCode(value = 100002,message = "密码验证错误")
    private String password;

    private String token;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }


    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
