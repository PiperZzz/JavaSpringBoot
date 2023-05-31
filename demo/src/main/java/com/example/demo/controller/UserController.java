package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.dao.DataIntegrityViolationException;

import com.example.demo.model.User;
import com.example.demo.model.UserResponse;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/submit")
    public @ResponseBody UserResponse submitUser(@RequestBody User user) {
        try {
            logger.info("Received User: " + user.getUsername());
            userRepository.save(user);
            return new UserResponse(true, "User has been added successfully");
        } catch (DataIntegrityViolationException  e) {
            logger.error("Error occurred while saving user: ", e);
            return new UserResponse(false, "Username or email already exists.");
        } catch (Exception e) {
            logger.error("Error occurred while saving user: ", e);
            return new UserResponse(false, "An error occurred while saving the user");
        }
    }
}