package com.example.fsp.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel
public class Result<T> {
    /**
     * 状态码，比如1000代表响应成功
     */
    @ApiModelProperty(value = "状态码", notes = "默认1000是成功")
    private int code;
    /**
     * 响应信息，用来说明响应情况
     */
    @ApiModelProperty(value = "响应信息", notes = "来说明响应情况")
    private String msg;
    /**
     * 响应的具体数据
     */
    @ApiModelProperty(value = "响应的具体数据")
    private T data;

    public Result(T data) {
        this(ResultCode.SUCCESS, data);
    }

    public Result(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
