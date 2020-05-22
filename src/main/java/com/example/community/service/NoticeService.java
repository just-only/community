package com.example.community.service;

import com.example.community.demo.*;
import com.example.community.dto.NoticeDto;
import com.example.community.exception.CommentException;
import com.example.community.exception.MyException;
import com.example.community.exception.QuestionException;
import com.example.community.exception.UserException;
import com.example.community.mapper.CommentMapper;
import com.example.community.mapper.NoticeMapper;
import com.example.community.mapper.QuestionMapper;
import com.example.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2018-2020
 * FileName: NoticeService
 * Author:   wdy
 * Date:     2020/5/21 17:33
 * Description: 通知Service
 */
@Service
public class NoticeService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private CommentMapper commentMapper;

    public List<NoticeDto> findAllByUserId(Integer id){
        List<NoticeDto> noticeDtos = new ArrayList<NoticeDto>();
        NoticeExample noticeExample = new NoticeExample();
        noticeExample.createCriteria().andReceiverEqualTo(Long.valueOf(id));
        List<Notice> notices = noticeMapper.selectByExample(noticeExample);
        for(Notice notice:notices){
            NoticeDto noticeDto = new NoticeDto();
            noticeDto.setNotice(notice);
            UserExample userExample = new UserExample();
            userExample.createCriteria().andAccountIdEqualTo(String.valueOf(notice.getSender()));
            User user = userMapper.selectByExample(userExample).get(0);
            if(user==null){
                throw new MyException(UserException.Notice_User_is_NUll);
            }
            noticeDto.setUser(user);
            Comment comment = commentMapper.selectByPrimaryKey(notice.getCommentId());
            if(comment==null){
                   throw new MyException(CommentException.Comment_Is_Null);
            }
            Question question = questionMapper.selectByPrimaryKey(Integer.valueOf(String.valueOf(comment.getQuestionId())));
            if(question==null){
                throw new MyException(QuestionException.Question_No_Found);
            }
               noticeDto.setQuestion(question);
               noticeDto.setComment(comment);
               noticeDtos.add(noticeDto);
        }
        return noticeDtos;
    }
    public Integer getNoticeCount(Integer id){
        NoticeExample noticeExample = new NoticeExample();
        noticeExample.createCriteria().andReceiverEqualTo(Long.valueOf(id)).andTypeEqualTo(0);
        long count = noticeMapper.countByExample(noticeExample);
        return Integer.valueOf((int) count);
    }

    public Integer getAllNoticeCount(Integer id){
        NoticeExample noticeExample = new NoticeExample();
        noticeExample.createCriteria().andReceiverEqualTo(Long.valueOf(id));
        long count = noticeMapper.countByExample(noticeExample);
        return Integer.valueOf((int) count);
    }
}
