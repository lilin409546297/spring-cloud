package com.cetc.service.impl;

import com.cetc.feign.client.TestFeign;
import com.cetc.service.IAdminClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminClientServiceImpl implements IAdminClientService{

    @Autowired
    private TestFeign testFeign;

    @Override
    public Integer getPort() {
        return testFeign.getPort();
    }
}
