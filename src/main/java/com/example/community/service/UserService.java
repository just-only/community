package com.example.community.service;

import com.example.community.demo.User;
import com.example.community.demo.UserExample;
import com.example.community.exception.MyException;
import com.example.community.exception.UserException;
import com.example.community.mapper.UserMapper;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2018-2020
 * FileName: UserService
 * Author:   wdy
 * Date:     2020/4/28 15:34
 * Description: 用户有关的数据增删改查
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    public User getUserByAccountId(Integer accountId){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(Integer.toString(accountId));
        User user = userMapper.selectByExample(userExample).get(0);
        if(user==null)
            throw new MyException(UserException.User_is_NUll);
        return user;
    }

    public User getUserById(Integer id){
        return userMapper.selectByPrimaryKey(Long.valueOf(id));
    }
}
