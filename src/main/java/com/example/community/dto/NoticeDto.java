package com.example.community.dto;

import com.example.community.demo.Comment;
import com.example.community.demo.Notice;
import com.example.community.demo.Question;
import com.example.community.demo.User;
import lombok.Data;

/**
 * Copyright (C), 2018-2020
 * FileName: NoticeDto
 * Author:   wdy
 * Date:     2020/5/21 17:33
 * Description: 根据Notice进行页面显示的类封装
 */
@Data
public class NoticeDto {
    private Notice notice;
    private Question question;
    private Comment comment;
    private User user;
}
