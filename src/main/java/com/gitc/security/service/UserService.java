package com.gitc.security.service;

import com.gitc.security.entity.RoleEntity;
import com.gitc.security.entity.UserEntity;
import com.gitc.security.repository.RoleRepository;
import com.gitc.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service("userService")
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(UserEntity user) {
        user.setActive(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(new RoleEntity("ADMIN")));
        userRepository.save(user);
    }
}
