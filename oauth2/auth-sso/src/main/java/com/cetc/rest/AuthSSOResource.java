package com.cetc.rest;

import com.cetc.service.IAuthSSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authSSO")
public class AuthSSOResource {

    @Autowired
    private IAuthSSOService feignService;

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/getPort")
    public Integer getPort() {
        return feignService.getPort();
    }

    @GetMapping("/getLocalPort")
    public Integer getLocalPort() {
        return port;
    }
}
