package com.example.community.dto;

import com.example.community.demo.Comment;
import com.example.community.demo.User;
import lombok.Data;

import java.util.ArrayList;

/**
 * Copyright (C), 2018-2020
 * FileName: VisitCommentDto
 * Author:   wdy
 * Date:     2020/4/28 14:50
 * Description: 页面显示的回复信息封装
 */
@Data
public class VisitCommentDto {
    private CommentDto commentDto;
    private ArrayList<CommentDto> commentDtos;
}
