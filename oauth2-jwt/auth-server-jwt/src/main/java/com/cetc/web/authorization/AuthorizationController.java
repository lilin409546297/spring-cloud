package com.cetc.web.authorization;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
//注意一定要加@SessionAttributes("authorizationRequest")代表这是认证请求
@SessionAttributes("authorizationRequest")
public class AuthorizationController {

    @RequestMapping("/oauth/confirm_access")
    public String authorization(Map<String, ?> map, HttpServletRequest request) {
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) map.get("authorizationRequest");
        request.setAttribute("clientId", authorizationRequest.getClientId());
        //这里的scope是一定要的，主要是认证需要传递的数据类型，需要scope
        request.setAttribute("scope", authorizationRequest.getScope());
        return "authorization";
    }
}