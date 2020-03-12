package com.adeng1024.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class RouterController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/page1")
    public String page1(){
        return "page1";
    }
    @GetMapping("/page2")
    public String page2(){
        return "page2";
    }
    @GetMapping("/page3")
    public String page3(){
        return "page3";
    }
    @GetMapping("/userLogin")
    public String login(){
        return "userLogin";
    }
    @RequestMapping(value = "/login/error",produces="text/html;charset=UTF-8;")
    public void loginError(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        Exception exception = (Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        System.out.println(exception.getMessage());
        try {
            response.getWriter().write(exception.getLocalizedMessage());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
