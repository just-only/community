package com.example.community.mappr;

import com.example.community.demo.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/*
 * Package: com.example.community.mappr
 * Description：
 * Author: weidongya
 * Date:  2020/3/24 16:20
 * Modified By:
 */
@Mapper
public interface QuestionMapper {

    @Insert("insert into question(description,title,create_time,modified_time,creator,comment_count,view_count,like_count,tag) " +
            "values(#{description},#{title},#{create_time},#{modified_time},#{creator},#{comment_count},#{view_count},#{like_count},#{tag})")
    void addQuestion(Question question);
}
