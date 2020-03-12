package com.adeng1024.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@EnableWebSecurity // 启用Spring Security的Web安全支持
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService customUserService() { // 注册UserDetailsService 的bean
        return new MyUserDetailService();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll();
//                .antMatchers("/page1/**").hasRole("page1")
//                .antMatchers("/page2/**").hasRole("user2")
//                .antMatchers("/page3/**").hasRole("user3");
        http.formLogin().loginPage("/userLogin").loginProcessingUrl("/login").failureUrl("/login/error");
        http.csrf().disable();
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/");
        http.rememberMe().rememberMeParameter("remember");
    }


    @Autowired
    MyUserDetailService myUserDetailService;
    @Autowired
    MyAuthenticationProvide myAuthenticationProvide;


    @Autowired
    public void config(AuthenticationManagerBuilder auth) throws Exception {
//         在内存中配置用户，配置多个用户调用`and()`方法
//        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder()) // 指定加密方式
//                .withUser("page1").password(passwordEncoder().encode("1234560")).roles("user1")
//                .and()
//                .withUser("page2").password(passwordEncoder().encode("1234560")).roles("user2")
//                .and()
//                .withUser("page3").password(passwordEncoder().encode("1234560")).roles("user3");
        try{

            auth.userDetailsService(myUserDetailService);
            auth.authenticationProvider(myAuthenticationProvide);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCryptPasswordEncoder：Spring Security 提供的加密工具，可快速实现加密加盐
        return new BCryptPasswordEncoder();
    }

}
