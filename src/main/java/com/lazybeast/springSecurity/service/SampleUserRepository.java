package com.lazybeast.springSecurity.service;

import com.lazybeast.springSecurity.config.Roles;
import com.lazybeast.springSecurity.dto.UserInfo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SampleUserRepository {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Optional<UserInfo> findUserByName(String name) {

        List<UserInfo> users = new ArrayList<UserInfo>(){{
            add(new UserInfo(1, "name1", "email1", passwordEncoder.encode("pass1"), Roles.ROLE_ADMIN + "," + Roles.ROLE_USER));
            add(new UserInfo(2, "name2", "email2", passwordEncoder.encode("pass2"), Roles.ROLE_USER));
        }};
        return users.stream()
                .filter(user -> user.getName().equals(name))
                .findAny();
    }
}
