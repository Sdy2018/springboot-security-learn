package com.adeng1024.config;



import com.adeng1024.customException.MyUsernameNotFoundException;
import com.adeng1024.pojo.CustomUserDetails;
import com.adeng1024.pojo.User;
import com.adeng1024.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException   {
        User user = userService.queryUserByUserName(s);
        List<String> auths = new ArrayList<>();
        if (user != null) {
            auths.add("ROLE_"+user.getPermission());
            auths.add("add");
            return new CustomUserDetails(user.getUsername(),user.getPassword(),auths);
        } else {
            throw new BadCredentialsException("user not exist");
        }
    }
}
