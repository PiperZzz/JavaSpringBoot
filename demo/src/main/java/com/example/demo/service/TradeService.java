package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.Wallet;
import com.example.demo.enums.OrderStatus;
import com.example.demo.enums.OrderType;
import com.example.demo.enums.Symbol;
import com.example.demo.exception.InsufficientBalanceException;
import com.example.demo.model.Asset;
import com.example.demo.model.Order;
import com.example.demo.repository.WalletRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.OrderRepository;

@Service
public class TradeService {
    private static final Logger logger= LoggerFactory.getLogger(TradeService.class);

    private final WalletRepository walletRepository;
    private final OrderRepository orderRepository;
    private final AssetRepository assetRepository;

    public TradeService(WalletRepository walletRepository, OrderRepository orderRepository, AssetRepository assetRepository) {
        this.walletRepository = walletRepository;
        this.orderRepository = orderRepository;
        this.assetRepository = assetRepository;
    }

    public void buy(User user, String symbol, double amount, double price) {
        logger.info("User {} Buying {} Amount of {} at Price {}", user, amount, symbol, price);

        Wallet wallet = walletRepository.findByUser(user);
        if (wallet.getBalance() < amount * price) {
            throw new InsufficientBalanceException();
        }
        wallet.setBalance(wallet.getBalance() - amount * price);
        walletRepository.save(wallet);

        Order order = new Order();
        order.setUser(user);
        order.setOrderType(OrderType.BUY);
        order.setSymbol(Symbol.valueOf(symbol));
        order.setAmount(amount);
        order.setOrderStatus(OrderStatus.OPEN);
        order.setOpenTime(LocalDateTime.now());
        orderRepository.save(order);
    }

    public void sell(User user, String symbol, double amount, double price) {
        logger.info("User {} Selling {} Amount of {} at Price {}", user, amount, symbol, price);

        Wallet wallet = walletRepository.findByUser(user);
        List<Asset> assets = assetRepository.findBySymbol(wallet);
        Optional<Asset> result = assets.stream().filter(asset -> asset.getSymbol().equals(Symbol.valueOf(symbol))).findFirst();
        if (result.isPresent()) {
            Asset asset = result.get();
            if (asset.getAmount() < amount) {
                throw new InsufficientBalanceException();
            }
            asset.setAmount(asset.getAmount() - amount);
            assetRepository.save(asset);
        } else {
            throw new InsufficientBalanceException();
        }
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