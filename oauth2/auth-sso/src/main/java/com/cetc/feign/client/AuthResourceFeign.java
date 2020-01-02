package com.cetc.feign.client;

import com.cetc.config.FeignConfiguration;
import com.cetc.feign.hystrix.AuthResourceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "auth-resource", configuration = {FeignConfiguration.class}, fallback = AuthResourceHystrix.class)
public interface AuthResourceFeign {

    @GetMapping("/api/authResource/getPort")
    Integer getPort();
}
