package com.cetc.service.impl;

import com.cetc.service.IRibbonService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonServiceImpl implements IRibbonService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "error")
    public Integer getPort() {
        return restTemplate.getForObject("http://client/api/test/getPort", Integer.class);
    }

    public Integer error() {
        return 0;
    }
}
