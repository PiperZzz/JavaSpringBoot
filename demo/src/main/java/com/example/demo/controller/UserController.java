package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.example.demo.model.User;
import com.example.demo.model.UserResponse;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/submit")
    public @ResponseBody UserResponse submitUser(@RequestBody User user) {
        try {
            logger.info("Received User: {}", user.getUsername());
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

    @GetMapping("/username")
    public ResponseEntity<String> getUsernameByEmail(@RequestParam String email) {
        try {
            logger.info("Received Email: {}", email);
            User user = userService.findByEmail(email);
            if (user != null) {
                logger.info("Find User: {}", user);
                return ResponseEntity.ok(user.getUsername());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}