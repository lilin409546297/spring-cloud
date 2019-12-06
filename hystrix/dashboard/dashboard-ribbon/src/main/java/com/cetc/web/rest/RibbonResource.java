package com.cetc.web.rest;

import com.cetc.service.IRibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboardRibbon")
public class RibbonResource {

    @Autowired
    private IRibbonService ribbonService;

    @GetMapping("/getPort")
    public Integer getPort() {
        return ribbonService.getPort();
    }
}