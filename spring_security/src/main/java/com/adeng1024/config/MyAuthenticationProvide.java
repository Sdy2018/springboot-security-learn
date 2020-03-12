package com.adeng1024.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component
public class MyAuthenticationProvide implements AuthenticationProvider {
    @Autowired
    private MyUserDetailService myUserDetailService;


    @Override
    public Authentication authenticate(Authentication authentication)  {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = myUserDetailService.loadUserByUsername(username);

        if(userDetails==null){
            throw new BadCredentialsException("user not exist");
        }

        if(!password.equals(userDetails.getPassword())){
            throw new BadCredentialsException("password error");
        }
        /*用户名密码认证成功*/
        return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
