package com.example.community.exception;

/**
 * Copyright (C), 2018-2020
 * FileName: MyException
 * Author:   wdy
 * Date:     2020/4/17 10:41
 * Description: 自定义异常类型
 */
public class MyException extends RuntimeException {
    private String message;
    public MyException(ExceptionMessage exceptionMessage){
        this.message=exceptionMessage.getMessage();
    }
    public String getMessage(){
        return message;
    }
}
