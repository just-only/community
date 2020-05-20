package com.example.community.service;

import com.example.community.demo.Question;
import com.example.community.mapper.QuestionExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2018-2020
 * FileName: QuestionService
 * Author:   wdy
 * Date:     2020/5/20 15:01
 * Description: 有关问题的操作
 */
@Service
public class QuestionService {

    @Autowired
    QuestionExtMapper questionExtMapper;

    public List<Question> findByTag(String tags){
        String[] tags1 = tags.split(",");
        List<Question> questions = new ArrayList<Question>();
        for(String tag:tags1){
            List<Question> questions1 = questionExtMapper.findQuestionByTag(tag);
            questions.addAll(questions1);
        }
        return questions;
    }

    public List<Question> findByTitle(String title){
        List<Question> questions = questionExtMapper.findByTitle(title);
        return questions;
    }
}
