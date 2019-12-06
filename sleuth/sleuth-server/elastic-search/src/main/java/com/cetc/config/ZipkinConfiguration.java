package com.cetc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.collector.CollectorSampler;


@Configuration
public class ZipkinConfiguration {

    @Bean
    public CollectorSampler collectorSampler() {
        return CollectorSampler.ALWAYS_SAMPLE;
    }

//    @Bean
//    public CollectorMetrics collectorMetrics() {
//        return InMemoryCollectorMetrics.NOOP_METRICS;
//    }
//
//    @Bean
//    public StorageComponent storageComponent() {
//        InMemoryStorage.Builder builder = new InMemoryStorage.Builder();
//        return builder.build();
//    }

}
