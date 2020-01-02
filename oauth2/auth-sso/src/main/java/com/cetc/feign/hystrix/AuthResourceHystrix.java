package com.cetc.feign.hystrix;

import com.cetc.feign.client.AuthResourceFeign;
import org.springframework.stereotype.Component;

@Component
public class AuthResourceHystrix implements AuthResourceFeign {
    @Override
    public Integer getPort() {
        return 0;
    }
}
