package com.example.demo.controller.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
