package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.dto.TradeRequest;
import com.example.demo.dao.UserRepository;
import com.example.demo.enums.Symbol;
import com.example.demo.model.User;
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
    public void buy(@RequestParam TradeRequest tradeRequest) {
        String username = tradeRequest.getUsername();
        String symbol = tradeRequest.getSymbol();
        if (!EnumUtil.contains(Symbol.class, symbol)) {
            throw new IllegalArgumentException("Invalid symbol");
        }
        User user = userRepository.findByUsername(username);
        tradeService.buy(user, symbol, tradeRequest.getAmount(), tradeRequest.getPrice());
    }

    @PostMapping("/sell")
    public void sell(@RequestParam TradeRequest tradeRequest) {
        String username = tradeRequest.getUsername();
        String symbol = tradeRequest.getSymbol();
        if (!EnumUtil.contains(Symbol.class, symbol)) {
            throw new IllegalArgumentException("Invalid symbol");
        }
        User user = userRepository.findByUsername(username);
        tradeService.sell(user, symbol, tradeRequest.getAmount(), tradeRequest.getPrice());
    }
}