package com.adeng1024.service;

import com.adeng1024.pojo.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();
    User queryUserByUserName(String username);
}