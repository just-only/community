package com.example.community.service;

import com.example.community.demo.*;
import com.example.community.dto.CommentDto;
import com.example.community.dto.VisitCommentDto;
import com.example.community.mapper.CommentMapper;
import com.example.community.mapper.QuestionMapper;
import com.example.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2018-2020
 * FileName: CommentService
 * Author:   wdy
 * Date:     2020/4/28 14:53
 * Description: 评论的查询处理
 */
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionMapper questionMapper;

    public List<VisitCommentDto> getComments(Integer questionId) {
        List<Comment> comments = new ArrayList<Comment>();//一级评论
        List<VisitCommentDto> visitCommentDtos = new ArrayList<VisitCommentDto>();//关于某一问题的所有评论的封装
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andQuestionIdEqualTo(questionId.longValue()).andParentIdEqualTo(0L);
        comments = commentMapper.selectByExample(commentExample); //查询所有的一级评论进行封装
        for (Comment comment : comments) { //进行所有评论的封装
            CommentDto commentDto = new CommentDto();//一级评论携带评论人的封装
            commentDto.setComment(comment);
            Question question = questionMapper.selectByPrimaryKey(comment.getQuestionId().intValue());//回复的评论
            commentDto.setReplyUser(userService.getUserByAccountId(question.getCreator()));//上级评论对应的用户
            commentDto.setUser(userService.getUserByAccountId(comment.getCommentorId().intValue()));//评论人添加
            VisitCommentDto visitCommentDto = new VisitCommentDto();
            CommentExample commentExample1 = new CommentExample();
            commentExample1.createCriteria().andTypeEqualTo(comment.getId().intValue());
            List<Comment> comments1 = commentMapper.selectByExample(commentExample1);//一级评论下所有的二级评论
            List<CommentDto> commentDtos = new ArrayList<CommentDto>();
            for(Comment comment1:comments1){//二级评论携带评论人进行封装
                CommentDto commentDto1 = new CommentDto();
                commentDto1.setComment(comment1);
                Comment comment2 = commentMapper.selectByPrimaryKey(comment1.getParentId());
                commentDto1.setReplyUser(userService.getUserByAccountId(comment2.getCommentorId().intValue()));
                commentDto1.setUser(userService.getUserByAccountId(comment1.getCommentorId().intValue()));
                commentDtos.add(commentDto1);
            }
            visitCommentDto.setCommentDto(commentDto);
            visitCommentDto.setCommentDtos((ArrayList<CommentDto>) commentDtos);//将一级评论下的所有二级评论封装
            visitCommentDtos.add(visitCommentDto);//将评论放置在所有评论的封装列表
        }
        return visitCommentDtos;
    }
}
