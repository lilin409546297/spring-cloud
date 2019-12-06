package com.cetc.feign.hystrix;

import com.cetc.feign.client.TestFeign;
import org.springframework.stereotype.Component;

@Component
public class TestHystrix implements TestFeign{

    @Override
    public Integer getPort() {
        return 0;
    }
}
