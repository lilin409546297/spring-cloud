package com.cetc.web.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RefreshScope
public class TestResource {

    @Value("${version}")
    private String version;

    @GetMapping("/getVersion")
    public String getVersion() {
        return version;
    }
}
