package com.cetc.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfiguration extends AuthorizationServerConfigurerAdapter{

    @Autowired
    private AuthDetailsService authDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Bean
    public ClientDetailsService clientDetailsService(HikariDataSource dataSource) {
        //使用数据库的配置方式
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public TokenStore tokenStore(HikariDataSource dataSource) {
        //token也使用数据的方式，后面会将JWT的使用方式
        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                //token获取方式
                .tokenKeyAccess("permitAll()")
                //检测加入权限
                .checkTokenAccess("isAuthenticated()")
                //允许表单认证
                .allowFormAuthenticationForClients();

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //这里就是具体的授权管理过程了
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //这里使用的认证方式为security配置方式
                .authenticationManager(authenticationManager)
                //提供get和post的认证方式
                .allowedTokenEndpointRequestMethods(HttpMethod.POST, HttpMethod.GET)
                //这里一定要配置userDetailsService，不然刷新token会出错，refresh_token
                .userDetailsService(authDetailsService)
                .tokenStore(tokenStore)
                //自定义认证页面
                .pathMapping("/oauth/confirm_access", "/oauth/confirm_access");
    }

}
