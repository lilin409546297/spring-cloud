package com.cetc.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = {"com.cetc.domain"}) //指定实体扫描路径
@EnableJpaRepositories(basePackages = {"com.cetc.repository"}) //指定repository扫描路径
@EnableJpaAuditing //开启jpa的审计功能BaseEntity中的@CreatedDate/@LastModifiedDate等
@EnableTransactionManagement //开启事务
public class DataSourceConfiguration {

}
