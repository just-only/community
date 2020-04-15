package com.example.community.mappr;

import com.example.community.demo.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    @Select("select * from question limit #{offset},#{size}")
    List<Question> findAll(@Param(value = "offset") int offset, @Param(value = "size") int size);

    @Select("select * from question where creator=#{userId} limit #{offset},#{size}")
    List<Question> findAllByUserId(@Param(value="userId") int userId,@Param(value = "offset") int offset, @Param(value = "size") int size);

    @Select("select count(*) from question")
    int count();

    @Select("select count(*) from question where creator=#{userId}")
    int countById(@Param(value="userId") int userId);

    @Select("select * from question where id=#{id}")
    Question grtById(@Param(value="id") Integer id);

    @Update("update question set tag=#{tag},title=#{title},description=#{description},modified_time=#{currentTimeMillis} where  id=#{id}")
    void updateQuestion(@Param(value="id") Integer id,
                        @Param(value="tag") String tag,
                        @Param(value="title") String title,
                        @Param(value="description")String description,
                        @Param(value="currentTimeMillis") long currentTimeMillis);
}
