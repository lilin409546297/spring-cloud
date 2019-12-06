package com.cetc.web.rest;

import com.cetc.config.CustomAutoProperties;
import com.cetc.config.CustomProperties;
import com.cetc.config.MyProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
@Api(tags = {"哈喽"})
public class HelloResource {

    @Autowired
    private CustomAutoProperties.AutoProperties autoProperties;

    @ApiOperation(value = "获取自定义配置")
    @GetMapping("/getCustomAuto")
    public String getCustomAuto() {
        return autoProperties.getName() + ":" + autoProperties.getAge();
    }

    @Autowired
    private CustomProperties customProperties;

    @GetMapping("/getCustom")
    public String getCustom() {
        return customProperties.getName() + ":" + customProperties.getAge();
    }

    @Autowired
    private MyProperties myProperties;

    @GetMapping("/getMy")
    public String getMy() {
        return myProperties.getName() + ":" + myProperties.getAge();
    }

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/getPort")
    public Integer port() {
        return port;
    }

    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "hello world!";
    }


}
