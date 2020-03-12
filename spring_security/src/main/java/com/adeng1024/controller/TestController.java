package com.adeng1024.controller;

import com.adeng1024.customException.CustomException;
import com.adeng1024.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class TestController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public String users(){
        return userService.getUserList().toString();
    }

    @GetMapping("/test")
    public String test(){
        throw new CustomException(12,"error");
    }
}
