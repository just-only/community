package com.example.community.exception;

public enum UserException implements ExceptionMessage{
    User_is_NUll("用户不存在！"),
    Notice_User_is_NUll("发起信息的人不存在！");
    private final String message;
    UserException(String message){ this.message=message;}
    @Override
    public String getMessage() {
        return null;
    }
}
