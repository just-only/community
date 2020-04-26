package com.example.community.exception;

/**
 * Copyright (C), 2018-2020
 * FileName: MyExceptionMessage
 * Author:   wdy
 * Date:     2020/4/17 10:39
 * Description: 自定义错误信息
 */
public enum  MyExceptionMessage {

    FILE_NO_FOUND("众里寻它千百度，它也不在文件最深处！");

    private String message;
    MyExceptionMessage(String message){
        this.message=message;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
