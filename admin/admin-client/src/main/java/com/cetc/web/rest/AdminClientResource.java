package com.cetc.web.rest;

import com.cetc.service.IAdminClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/adminClient")
public class AdminClientResource {

    @Autowired
    private IAdminClientService adminClientService;

    @GetMapping("/getPort")
    public Integer getPort() {
        return adminClientService.getPort();
    }
}
