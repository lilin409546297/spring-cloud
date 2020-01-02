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

    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    public AuthorizationCodeResourceDetails authorizationCodeResourceDetails() {
        return new AuthorizationCodeResourceDetails();
    }

    @Autowired
    private AuthorizationCodeResourceDetails authorizationCodeResourceDetails;

    @Autowired
    @Qualifier("oauth2ClientContext")
    private OAuth2ClientContext oAuth2ClientContext;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new OAuth2FeignRequestInterceptor(oAuth2ClientContext, authorizationCodeResourceDetails);
    }

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate() {
        return new OAuth2RestTemplate(authorizationCodeResourceDetails);
    }
}