package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;

@Service
public class TradingService {
    private static final Logger logger= LoggerFactory.getLogger(TradingService.class);

    private final UserRepository userRepository;

    public TradingService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void buy(String symbol, int amount) {
        logger.info("Buying {} amount of {}", amount, symbol);
    }

    public void sell(String symbol, int amount) {
        logger.info("Selling {} amount of {}", amount, symbol);
    }

    public void hold(String symbol, int amount) {
        logger.info("Holding {} amount of {}", amount, symbol);
    }

    public void cancel(String symbol, int amount) {
        logger.info("Canceling {} amount of {}", amount, symbol);
    }

    public void update(String symbol, int amount) {
        logger.info("Updating {} amount of {}", amount, symbol);
    }
}