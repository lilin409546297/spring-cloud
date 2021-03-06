package com.cetc.service.impl;

import com.cetc.feign.client.TestFeign;
import com.cetc.service.IAuthResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthResourceServiceImpl implements IAuthResourceService {

    @Autowired
    private TestFeign testFeign;

    @Override
    public Integer getPort() {
        return testFeign.getPort();
    }
}
