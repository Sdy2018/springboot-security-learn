package com.adeng1024.service.impl;


import com.adeng1024.mapper.UserMapper;
import com.adeng1024.pojo.User;
import com.adeng1024.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public User queryUserByUserName(String username) {
        return userMapper.queryUserByUserName(username);
    }

}
