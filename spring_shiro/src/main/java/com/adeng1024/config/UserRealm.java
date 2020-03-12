package com.adeng1024.config;

import com.adeng1024.pojo.User;
import com.adeng1024.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("-----授权了-----AuthorizationInfo");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();//其实就是拿认证成功的时候的那个user
        info.addStringPermission(currentUser.getPermission());
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("-----认证了-----AuthenticationInfo");
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        //用户名，密码去数据库取
        System.out.println(userToken.getUsername());
        User user = userService.queryUserByUserName(userToken.getUsername());
        if (user==null){  //没有这个人
            return null;  //其实就是抛出UnknownAccountException异常
        }
        //之后密码认证，shiro   它自己会做
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user,user.getPassword(),"");
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",user);
        return info;
    }
}
