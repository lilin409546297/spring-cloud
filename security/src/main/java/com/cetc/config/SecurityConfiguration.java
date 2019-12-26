package com.cetc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthDetailsService authDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
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
//            .antMatchers("/").hasRole("ADMIN")
            .antMatchers("/").hasAuthority("ADMIN")
            .anyRequest().authenticated()
        .and()
            .formLogin()
            .loginPage("/login.html")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/")
        .and()
            .exceptionHandling().accessDeniedPage("/no-login")
        .and()
            .logout()
            .logoutSuccessUrl("/")
            .deleteCookies("JSESSIONID")
        .and()
            .httpBasic().disable();
    }

}
