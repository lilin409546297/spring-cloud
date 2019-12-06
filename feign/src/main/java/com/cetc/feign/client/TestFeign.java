package com.cetc.feign.client;

import com.cetc.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "client", configuration = {FeignConfiguration.class})
public interface TestFeign {

    @GetMapping("/api/test/getPort")
    Integer getPort();
}
