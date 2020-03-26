package com.example.community.mappr;

import com.example.community.demo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Package: com.example.community.mappr
 * Description：Mybatis的UserMapper类
 * Author: weidongya
 * Date:  2020/3/22 18:27
 * Modified By:
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user(account_id,name,token,create_time,modified,bio,image_url) values(#{account_id},#{name},#{token},#{create_time},#{modified},#{bio},#{image_url})")
    void insertUser(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("SELECT DISTINCT ACCOUNT_ID,`NAME`,bio,image_url FROM `user` where account_id=#{account_id}")
    User findByAccount_id(String account_id);
}
