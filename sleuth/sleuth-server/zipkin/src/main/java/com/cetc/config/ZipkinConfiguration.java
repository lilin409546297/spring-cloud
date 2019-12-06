package com.cetc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zipkin2.collector.CollectorMetrics;
import zipkin2.collector.CollectorSampler;
import zipkin2.collector.InMemoryCollectorMetrics;
import zipkin2.storage.InMemoryStorage;
import zipkin2.storage.StorageComponent;



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
