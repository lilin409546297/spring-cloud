package com.cetc.init;

import com.cetc.domain.Role;
import com.cetc.domain.User;
import com.cetc.repository.RoleRepository;
import com.cetc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
public class InitData implements CommandLineRunner{

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InitData(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
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
