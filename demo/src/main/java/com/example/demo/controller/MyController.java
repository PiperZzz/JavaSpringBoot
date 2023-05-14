package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class MyController {
    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

    @PostMapping("/submit")
    public @ResponseBody String submitString(@RequestBody String input) {
        logger.info("Received: " + input);
        return input + "!";
    }
}