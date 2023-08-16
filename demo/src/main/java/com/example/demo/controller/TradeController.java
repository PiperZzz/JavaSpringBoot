package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.dto.OrderRequest;
import com.example.demo.dao.UserRepository;
import com.example.demo.enums.SymbolCode;
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

    @PostMapping("/create-order")
    public void createOrder(@RequestParam OrderRequest orderRequest) {
        //TODO Refactor this method for general order creation
        //TODO order validation: price range, quantity range, balance, symbol existence
        
        String symbol = orderRequest.getSymbol();
        if (!EnumUtil.contains(SymbolCode.class, symbol)) {
            throw new IllegalArgumentException("Invalid symbol");
        }

        String username = orderRequest.getUsername();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("Invalid user");
        }



        tradeService.createOrder(user, symbol, orderRequest.getQuantity(), orderRequest.getLmitePrice());
    }

    @PostMapping("/cancel-order")
    public void cancel(@RequestParam OrderRequest tradeRequest) {
        //TODO Refactor this method for general order cancellation
        String username = tradeRequest.getUsername();
        String symbol = tradeRequest.getSymbol();
        if (!EnumUtil.contains(SymbolCode.class, symbol)) {
            throw new IllegalArgumentException("Invalid symbol");
        }
        User user = userRepository.findByUsername(username);
        tradeService.cancelOrder(user, symbol, tradeRequest.getQuantity(), tradeRequest.getLmitePrice());
    }
}