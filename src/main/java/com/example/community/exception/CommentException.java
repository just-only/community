package com.example.community.exception;

public enum CommentException implements ExceptionMessage {
    User_Is_NUll("用户未登录,不能评论哦！"),
    Comment_Insert_Fail("注意哦，评论未记录，请重试吧！"),
    Comment_Is_Null("看清楚哦，评论内容为空！");
    private String message;
    CommentException(String message){
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
