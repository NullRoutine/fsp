package com.example.fsp.bean;

import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(1000, "操作成功"),

    FAILED(1001, "响应失败"),

    VALIDATE_FAILED(1002, "参数校验失败"),

    AUTH_ERROR(6000, "认证错误"),

    ERROR(5000, "未知错误");


    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
