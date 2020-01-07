package com.cetc.service.impl;

import com.cetc.feign.client.TestFeign;
import com.cetc.service.IAuthResourceJwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthResourceJwtServiceImpl implements IAuthResourceJwtService {

    @Autowired
    private TestFeign testFeign;

    @Override
    public Integer getPort() {
        return testFeign.getPort();
    }
}
