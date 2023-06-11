package com.example.demo.model;

import lombok.Data;

@Data
public class UserResponse {
    private boolean success;
    private String message;

    public UserResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}