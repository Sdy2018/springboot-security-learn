package com.adeng1024.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @GetMapping(value = {"/","/index"})
    public String index(){
        return "index";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    //这个url需要add权限
    @RequiresPermissions("add")
    @GetMapping("/user/add")
    public String add(){
        return "user/add";
    }
    //这个url需要update权限
    @RequiresPermissions("update")
    @GetMapping("/user/update")
    public String update(){
        return "user/update";
    }
    @PostMapping("/login")
    public String login(String username, String password, Model model){
        //获取当前输入的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //登录，没有异常就说明登录成功
        try {
            //这个token会传到userRealm域的认证方法中
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg","用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }
    @GetMapping("/unAuth")
    public String unAuth(){
        return "/unAuth";
    }
    @GetMapping("/logout")
    public String logout(){
        Subject currentSubject = SecurityUtils.getSubject();
        currentSubject.logout();
        return "redirect:/index";
    }
}
