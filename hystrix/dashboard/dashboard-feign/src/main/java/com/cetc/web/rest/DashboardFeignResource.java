package com.cetc.web.rest;

import com.cetc.service.IFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboardFeign")
public class DashboardFeignResource {

    @Autowired
    private IFeignService feignService;

    @GetMapping("/getPort")
    public Integer getPort() {
        return feignService.getPort();
    }
}
