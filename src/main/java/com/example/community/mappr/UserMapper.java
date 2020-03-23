package com.example.community.mappr;

import com.example.community.demo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Package: com.example.community.mappr
 * Description：Mybatis的UserMapper类
 * Author: weidongya
 * Date:  2020/3/22 18:27
 * Modified By:
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user(account_id,name,token,create_time,modified) values(#{account_id},#{name},#{token},#{create_time},#{modified})")
    void insertUser(User user);
}
