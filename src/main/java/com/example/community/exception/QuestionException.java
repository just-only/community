package com.example.community.exception;

public enum QuestionException implements ExceptionMessage {
    Question_No_Found("访问的问题不存在哦！"),
    User_is_NUll("用户未登录！");
    private String message;
    QuestionException(String message){this.message=message;}
    @Override
    public String getMessage() {
        return message;
    }
}
