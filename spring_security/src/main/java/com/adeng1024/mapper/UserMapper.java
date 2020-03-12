package com.adeng1024.mapper;


import com.adeng1024.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    List<User> getUserList();
    User queryUserByUserName(String username);
}
