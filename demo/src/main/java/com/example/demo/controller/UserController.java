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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.example.demo.model.User;
import com.example.demo.model.UserResponse;
import com.example.demo.service.JwtTokenProvider;
import com.example.demo.service.UserService;
import com.example.demo.controller.dto.LoginResponse;
import com.example.demo.controller.dto.ErrorResponse;
import com.example.demo.controller.dto.LoginRequest;

@RestController
@RequestMapping("/api")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final JwtTokenProvider tokenProvider;

    public UserController(UserService userService, JwtTokenProvider tokenProvider) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/register")
    public @ResponseBody UserResponse submitUser(@RequestBody User user) {
        try {
            logger.info("Received User: {}", user.getUsername());
            userService.save(user);
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());

            String token = tokenProvider.generateToken(authentication);

            return ResponseEntity.ok(new LoginResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Invalid username or password"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("An error occurred during login"));
        }
    }
}