package com.cetc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .anyRequest()
            .authenticated()
        .and()
            //说明：这里和官网不同的是，因为默认端口为/login，所以我这里直接放开login.html就可以了不用配置loginProcessingUrl
            //在说明一点，这里是采用的本地页面的。如果前后端分开，请配具体的登录接口。
            .formLogin()
            .loginPage("/login.html").permitAll()
        .and()
            //默认接口/logout，不用配置logoutUrl
            .logout()
            .logoutSuccessUrl("/login.html")
        .and()
            //这里必须加入httpBasic，因为Eureka-Server是基于最原始的方式进行验证的。
            .httpBasic();
    }
}
