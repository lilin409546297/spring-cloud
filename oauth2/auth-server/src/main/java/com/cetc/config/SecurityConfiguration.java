package com.cetc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//开启注解的使用
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //认证过程
    @Autowired
    private AuthDetailsService authDetailsService;

    //加密方式
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authDetailsService).passwordEncoder(passwordEncoder);
    }

    //基本没有什么太大的变化
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .exceptionHandling()
        .and()
            .authorizeRequests()
            .antMatchers("/login.html").permitAll()
            .anyRequest().authenticated()
        .and()
            .formLogin()
            //注意我这里使用的是自定义的登录页面
            .loginPage("/login.html")
            .loginProcessingUrl("/login")
        .and()
            .logout()
            .deleteCookies("SESSION_AUTH_SERVER");
    }

    //注意加入bean主要是在authentication server的配置需要使用
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
