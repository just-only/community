package com.example.community.controller;

import com.example.community.JsonDto.JsonComment;
import com.example.community.demo.*;
import com.example.community.dto.CommentDto;
import com.example.community.exception.CommentException;
import com.example.community.exception.MyException;
import com.example.community.exception.UserException;
import com.example.community.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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

    @Autowired
    QuestionExtMapper questionExtMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    CommentExtMapper commentExtMapper;

    @Autowired
    NoticeMapper noticeMapper;

    @Transactional
    @ResponseBody
    @PostMapping("/comment/replay")
    public String getReplayUser(@RequestBody JsonComment jsonComment,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return CommentException.User_Is_NUll.getMessage();
        }
        if(jsonComment.getCommentText().isEmpty()){
            return CommentException.Comment_Is_Null.getMessage();
        }
        Comment comment = new Comment();
        comment.setCommentText(jsonComment.getCommentText());
        comment.setParentId(jsonComment.getParentId().longValue());
        comment.setCommentorId(jsonComment.getCommentor().longValue());
        comment.setQuestionId(jsonComment.getQuestionId().longValue());
        comment.setCreateTime(System.currentTimeMillis());
        comment.setModefied(System.currentTimeMillis());
        comment.setType(jsonComment.getType());
        comment.setLikeCount(0L);
        commentMapper.insert(comment);
        Question question = questionMapper.selectByPrimaryKey(jsonComment.getQuestionId());
        Notice notice = new Notice();
        Long maxCommentId =Long.valueOf( commentExtMapper.findMaxCommentId());
        notice.setCommentId(maxCommentId);
        notice.setSender(Long.valueOf(user.getAccountId()));
        notice.setType(0);
        int parentId = jsonComment.getParentId();
        if(parentId!=0){
            notice.setReceiver(commentMapper.selectByPrimaryKey(Long.valueOf(parentId)).getCommentorId());
        }else{
            notice.setReceiver(questionMapper.selectByPrimaryKey(question.getId()).getCreator().longValue());
        }
        noticeMapper.insert(notice);
        int n = questionExtMapper.addComment(question);
        if(n!=0){
            return "success";
        }else{
            return "fail";
        }
    }

    @ResponseBody
    @PostMapping("/comment/giveup")
    public String commentGiveUp(@RequestParam(name="id") String id){
         Comment comment = commentMapper.selectByPrimaryKey(Long.valueOf(id));
         commentExtMapper.addCommentLike(comment);
         return "sucess";
    }
}
