package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.dto.OrderRequest;
import com.example.demo.dao.UserRepository;
import com.example.demo.enums.OrderDirection;
import com.example.demo.enums.SymbolCode;
import com.example.demo.model.User;
import com.example.demo.model.Asset;
import com.example.demo.service.OrderService;
import com.example.demo.util.EnumUtil;

import java.util.List;

@RestController
@RequestMapping("/api/trade")
public class OrderController {

    private final OrderService tradeService;
    private final UserRepository userRepository;

    public OrderController(OrderService orderService, UserRepository userRepository) {
        this.tradeService = orderService;
        this.userRepository = userRepository;
    }

    @PostMapping("/create-order")
    public void createOrder(@RequestParam OrderRequest orderRequest) {
        
        String symbol = orderRequest.getSymbol();
        if (!EnumUtil.contains(SymbolCode.class, symbol)) {
            throw new IllegalArgumentException("Invalid symbol");
        }

        String orderDirection = orderRequest.getOrderDirection();
        if (!EnumUtil.contains(OrderDirection.class, orderDirection)) {
            throw new IllegalArgumentException("Invalid order direction");
        }

        String username = orderRequest.getUsername();
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("Invalid user");
        }
        
        if (!orderRequest.isMarketOrder() &&  (orderRequest.getExcutionPrice() <= 0)) {
            throw new IllegalArgumentException("Invalid limit price");
        }

        if (orderRequest.getQuantity() <= 0) {
            throw new IllegalArgumentException("Invalid quantity");
        }

        double total = orderRequest.getExcutionPrice() * orderRequest.getQuantity();
        List<Asset> assets = user.getWallet().getAssets();
        double assetAmount = assets.stream().filter(asset -> asset.getSymbol().equals(symbol)).mapToDouble(Asset::getAmount).sum(); //TODO double check

        if (!orderRequest.isMarketOrder()) {
            if (orderDirection.equals(OrderDirection.BUY.toString()) && total > user.getWallet().getBalance()) {
                throw new IllegalArgumentException("Insufficient balance");
            } else if (orderDirection.equals(OrderDirection.SELL.toString()) && assetAmount < orderRequest.getQuantity()) {
                throw new IllegalArgumentException("Insufficient asset");
            }
        }

        if (orderRequest.isStopOrder() && orderRequest.getStopPrice() <= 0) {
            throw new IllegalArgumentException("Invalid stop price");
        }

        if (orderRequest.getExpirationTime() < 0) {
            throw new IllegalArgumentException("Invalid expiration time");
        } else if (orderRequest.getExpirationTime() == 0) {
            orderRequest.setExpirationTime(1000 * 60 * 60 * 24 * 7); //TODO default expiration time
        }

        tradeService.createOrder(user, orderRequest);
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
        tradeService.cancelOrder(user, symbol, tradeRequest.getQuantity(), tradeRequest.getExcutionPrice());
    }
}