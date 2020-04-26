package com.example.community.dto;

import lombok.Data;

import java.math.BigInteger;

/**
 * Copyright (C), 2018-2020
 * FileName: CommentDto
 * Author:   wdy
 * Date:     2020/4/17 15:28
 * Description: 评论封装的josn对象
 */
@Data
public class CommentDto {
    private Long questionId;
    private String commentText;
    private int type;
}
