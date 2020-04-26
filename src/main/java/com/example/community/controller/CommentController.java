package com.example.community.controller;

import com.example.community.demo.Comment;
import com.example.community.demo.User;
import com.example.community.dto.CommentDto;
import com.example.community.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright (C), 2018-2020
 * FileName: CommentController
 * Author:   wdy
 * Date:     2020/4/17 15:18
 * Description: 评论处理
 */
@Controller
public class CommentController {

    @Autowired
    CommentMapper commentMapper;

    @PostMapping("/comment")
    public String post(@RequestBody CommentDto commentDto){
        System.out.println(commentDto);
        Comment comment = new Comment();
        comment.setQuestionId(commentDto.getQuestionId());
        comment.setCommentTxt(commentDto.getCommentText());
        comment.setType(commentDto.getType());
        comment.setCreateTime(System.currentTimeMillis());
        //User user = (User) request.getSession().getAttribute("user");
        comment.setModefied(System.currentTimeMillis());
        comment.setCommentorId((long) 1);
        comment.setLikeCount(10L);
        commentMapper.insert(comment);
        return "/";
    }
}
