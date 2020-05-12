package com.example.community.mapper;

import com.example.community.demo.Question;
import com.example.community.demo.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {
    int intView(Question record);
    int addComment(Question record);
}