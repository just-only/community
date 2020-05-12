package com.example.community.exception;

public enum QuestionExceptionMessage implements ExceptionMessage {
    Question_No_Found("访问的问题不存在哦！");
    private String message;
    QuestionExceptionMessage(String message){this.message=message;}
    @Override
    public String getMessage() {
        return message;
    }
}
