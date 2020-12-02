package com.example.fsp.config;

import com.example.fsp.bean.ResultCode;
import lombok.Getter;

@Getter
public class APIException extends RuntimeException {
    private ResultCode code;
    private String msg;

    public APIException(String msg) {
        this(ResultCode.ERROR, msg);
    }

    public APIException(ResultCode code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
