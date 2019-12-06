package com.cetc.web.rest;

import com.cetc.service.IRibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hystrixRibbon")
public class RibbonResource {

    @Autowired
    private IRibbonService ribbonService;

    @GetMapping("/getPort")
    public Integer getPort() {
        return ribbonService.getPort();
    }
}