package com.cetc.web.authorization;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@SessionAttributes("authorizationRequest")
public class AuthorizationController {

    @RequestMapping("/oauth/confirm_access")
    public String authorization(Map<String, ?> map, HttpServletRequest request) {
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) map.get("authorizationRequest");
        request.setAttribute("clientId", authorizationRequest.getClientId());
        request.setAttribute("scope", authorizationRequest.getScope());
        return "authorization";
    }
}
