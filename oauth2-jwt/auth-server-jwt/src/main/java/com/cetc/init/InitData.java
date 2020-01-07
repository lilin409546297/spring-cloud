package com.cetc.init;

import com.cetc.domain.OauthClientDetails;
import com.cetc.domain.Role;
import com.cetc.domain.User;
import com.cetc.repository.OauthClientDetailsRepository;
import com.cetc.repository.RoleRepository;
import com.cetc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;


@Component
public class InitData implements CommandLineRunner{

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final OauthClientDetailsRepository oauthClientDetailsRepository;

    @Autowired
    public InitData(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, OauthClientDetailsRepository oauthClientDetailsRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.oauthClientDetailsRepository = oauthClientDetailsRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        initUser();
        initOauth2();
    }

    private void initOauth2() {
        List<OauthClientDetails> oauthClientDetailsList = oauthClientDetailsRepository.findAll();
        if (oauthClientDetailsList.size() == 0) {
            OauthClientDetails oauthClientDetails = new OauthClientDetails();
            oauthClientDetails.setClientId("client_jwt");
            oauthClientDetails.setClientSecret(passwordEncoder.encode("secret_jwt"));
            oauthClientDetails.setAuthorizedGrantTypes("authorization_code,refresh_token,client_credentials,password,implicit");
            oauthClientDetails.setScope("ALL");
            oauthClientDetails.setAuthorities("ROLE_USER");
            oauthClientDetails.setWebServerRedirectUri("http://127.0.0.1:8698");
            oauthClientDetails.setAutoapprove("false");

            OauthClientDetails oauthClientDetailsSSOJwt = new OauthClientDetails();
            oauthClientDetailsSSOJwt.setClientId("client_sso_jwt");
            oauthClientDetailsSSOJwt.setClientSecret(passwordEncoder.encode("secret_sso_jwt"));
            oauthClientDetailsSSOJwt.setAuthorizedGrantTypes("authorization_code");
            oauthClientDetailsSSOJwt.setScope("ALL");
            oauthClientDetailsSSOJwt.setAuthorities("ROLE_ADMIN");
            oauthClientDetailsSSOJwt.setWebServerRedirectUri("http://127.0.0.1:8699");
            oauthClientDetailsSSOJwt.setAutoapprove("true");

            oauthClientDetailsRepository.save(oauthClientDetails);
            oauthClientDetailsRepository.save(oauthClientDetailsSSOJwt);
        }
    }

    private void initUser() {
        List<User> users = userRepository.findAll();
        if (users.size() == 0) {
            Role role1 = new Role();
            role1.setRoleName("管理员");
            role1.setRoleType("ADMIN");
            Role role2 = new Role();
            role2.setRoleName("用户");
            role2.setRoleType("USER");

            role1 = roleRepository.save(role1);
            role2 = roleRepository.save(role2);

            User user1 = new User();
            user1.setUsername("admin");
            user1.setPassword(passwordEncoder.encode("admin"));
            user1.setRoles(Collections.singletonList(role1));
            User user2 = new User();
            user2.setUsername("user");
            user2.setPassword(passwordEncoder.encode("user"));
            user2.setRoles(Collections.singletonList(role2));

            userRepository.save(user1);
            userRepository.save(user2);
        }
    }
}
