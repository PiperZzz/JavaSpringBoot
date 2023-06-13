package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import com.example.demo.repository.UserRepository;
import com.example.demo.model.User;
// import org.springframework.cache.annotation.Cacheable;

@Service
public class UserService {
    private final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public long getUserCount() {
        return userRepository.count();
    }

    // @Cacheable(value = "users", key = "#email")
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Authentication authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            return authentication;
        }

        return null;
    }
}