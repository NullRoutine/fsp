package com.example.fsp.config;

import com.example.fsp.bean.Result;
import com.example.fsp.bean.ResultCode;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    //    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Result<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
//        // 从异常对象中拿到ObjectError对象
//        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
//        // 然后提取错误提示信息进行返回
//        return new Result<>(ResultCode.VALIDATE_FAILED, objectError.getDefaultMessage());
//    }
    @ExceptionHandler(APIException.class)
    public String APIExceptionHandler(APIException e) {
        return e.getMsg();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) throws NoSuchFieldException {
        String defaultMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        Class<?> parameterType = e.getParameter().getParameterType();
        String fieldName = e.getBindingResult().getFieldError().getField();
        Field field = parameterType.getDeclaredField(fieldName);
        // 获取Field对象上的自定义注解
        ExceptionCode annotation = field.getAnnotation(ExceptionCode.class);
        if(annotation!=null){
            return new Result<>(annotation.value(),annotation.message(),defaultMessage);
        }
        return new Result<>(ResultCode.VALIDATE_FAILED, defaultMessage);
    }
}
