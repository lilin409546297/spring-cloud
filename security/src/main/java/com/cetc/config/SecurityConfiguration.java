package com.cetc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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

    //加密方式，可以使用默认，或者其他方式
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        在内存中进行认证
//        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("admin")).authorities("ADMIN");
//        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder.encode("user")).authorities("USER");
        auth.userDetailsService(authDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/login.html").permitAll()
//           注意如果是使用数据库时，数据库一定要使用ROLE_作为前缀。具体验证参考SecurityContextHolderAwareRequestFilter
//           .antMatchers("/").hasRole("ADMIN")
            //这里使用认证的方式，是和数据库一样的管理方式。
            .antMatchers("/").hasAuthority("ADMIN")
            .anyRequest().authenticated()
        .and()
            .formLogin()
            .loginPage("/login.html")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/")
        .and()
            .exceptionHandling().accessDeniedPage("/no-permission")
        .and()
            .logout()
            .logoutSuccessUrl("/")
            .deleteCookies("JSESSIONID")
        .and()
            .httpBasic().disable();
    }
}
