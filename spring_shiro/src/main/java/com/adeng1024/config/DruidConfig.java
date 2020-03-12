package com.adeng1024.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    //bean 等於javaweb中的xml
    @Bean
    public DataSource dataSource(){
        return new DruidDataSource();
    }

    //配置servlet
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.addUrlMappings("/druid/*");
        servletRegistrationBean.setServlet(new StatViewServlet());
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("loginUsername", "adeng1024");
        initParameters.put("loginPassword", "123456");
        //后台允许谁可以访问
        //initParams.put("allow", "localhost")：表示只有本机可以访问
        //initParams.put("allow", "")：为空或者为null时，表示允许所有访问
        initParameters.put("allow", "");
        //deny：Druid 后台拒绝谁访问
        //initParams.put("adeng1024", "192.168.1.0");表示禁止此ip访问

        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());

        //exclusions：设置哪些请求进行过滤排除掉，从而不进行统计
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("exclusions", "*.js,*.css,/druid/*");
        filterRegistrationBean.setInitParameters(initParameters);

        //"/*" 表示过滤所有请求
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));

        return filterRegistrationBean;
    }
}
