package com.adeng1024.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    private List<String> authList;

    public CustomUserDetails(String username, String password, List authList) {
        this.username = username;
        this.password = password;
        this.authList = authList;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
//        //设置权限
//        auths.add(new SimpleGrantedAuthority(permission));
//        //前面有ROLE_的表示设置角色,获取需去掉ROLE_
//        auths.add(new SimpleGrantedAuthority("ROLE_user2"));
        for(String auth:authList){
            auths.add(new SimpleGrantedAuthority(auth));
        }
        return auths;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
