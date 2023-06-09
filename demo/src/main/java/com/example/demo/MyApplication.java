package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MyApplication {
	private static final Logger logger = LoggerFactory.getLogger(MyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
		logger.info("Server Ready!");
	}
}