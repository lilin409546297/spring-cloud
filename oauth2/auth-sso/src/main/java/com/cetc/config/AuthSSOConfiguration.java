package com.cetc.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

@Configuration
public class AuthSSOConfiguration {

    //获取认证的配置数据
    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    public AuthorizationCodeResourceDetails authorizationCodeResourceDetails() {
        return new AuthorizationCodeResourceDetails();
    }

    @Autowired
    private AuthorizationCodeResourceDetails authorizationCodeResourceDetails;

    //这里使用他配置好的OAuth2ClientContext，这里有坑自己体会
    @Autowired
    @Qualifier("oauth2ClientContext")
    private OAuth2ClientContext oAuth2ClientContext;

    //配置请求拦截
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new OAuth2FeignRequestInterceptor(oAuth2ClientContext, authorizationCodeResourceDetails);
    }

    //oauth2认证配置
    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate() {
        return new OAuth2RestTemplate(authorizationCodeResourceDetails);
    }
}