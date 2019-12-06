package com.cetc.web.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestResource {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/getPort")
    public Integer getPort() {
        return port;
    }
}
