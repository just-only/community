package com.example.community.mapper;

import com.example.community.demo.Question;
import com.example.community.demo.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {
    int intView(Question record);
    int addComment(Question record);
    int addLike(Question record);
    List<Question> findQuestionByTag(String tag);
    List<Question> findViewMaxQuestion();
    List<Question> findByTitle(String title,int page,int size);
}