package com.cetc.service.impl;

import com.cetc.feign.client.AuthResourceFeign;
import com.cetc.service.IAuthSSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthSSOServiceImpl implements IAuthSSOService {

    @Autowired
    private AuthResourceFeign authResourceFeign;

    @Override
    public Integer getPort() {
        return authResourceFeign.getPort();
    }
}
