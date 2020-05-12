package com.example.community.exception;

public enum VisitException implements ExceptionMessage {

    User_Is_NUll("没有登陆哦！请先登录！"),
    File_No_Found("资源没找到哦，请注意访问地址！"),
    System_Error("后台代码出错，请及时联系管理员哦！");
    private final String message;

    VisitException(String message){ this.message=message; }

    @Override
    public String getMessage() { return this.message; }
}
