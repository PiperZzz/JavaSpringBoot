package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.enums.Symbol;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TradeService;
import com.example.demo.util.EnumUtil;

@RestController
@RequestMapping("/api/trade")
public class TradeController {

    private final TradeService tradeService;
    private final UserRepository userRepository;

    public TradeController(TradeService tradingService, UserRepository userRepository) {
        this.tradeService = tradingService;
        this.userRepository = userRepository;
    }

    @PostMapping("/buy")
    public void buy(@RequestParam String username, @RequestParam String symbol, @RequestParam double amount, @RequestParam double price) {
        if (!EnumUtil.contains(Symbol.class, symbol)) {
            throw new IllegalArgumentException("Invalid symbol");
        }
        User user = userRepository.findByUsername(username);
        tradeService.buy(user, symbol, amount, price);
    }

    @PostMapping("/sell")
    public void sell(@RequestParam String username, @RequestParam String symbol, @RequestParam double amount, @RequestParam double price) {
        if (!EnumUtil.contains(Symbol.class, symbol)) {
            throw new IllegalArgumentException("Invalid symbol");
        }
        User user = userRepository.findByUsername(username);
        tradeService.sell(user, symbol, amount, price);
    }
}
