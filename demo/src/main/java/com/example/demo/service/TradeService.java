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
import com.example.demo.model.UserOrder;
import com.example.demo.repository.WalletRepository;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.UserOrderRepository;

@Service
public class TradeService {
    private static final Logger logger= LoggerFactory.getLogger(TradeService.class);

    private final WalletRepository walletRepository;
    private final UserOrderRepository userOrderRepository;
    private final AssetRepository assetRepository;

    public TradeService(WalletRepository walletRepository, UserOrderRepository userOrderRepository, AssetRepository assetRepository) {
        this.walletRepository = walletRepository;
        this.userOrderRepository = userOrderRepository;
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

        UserOrder userOrder = new UserOrder();
        userOrder.setUser(user);
        userOrder.setOrderType(OrderType.BUY);
        userOrder.setSymbol(Symbol.valueOf(symbol));
        userOrder.setAmount(amount);
        userOrder.setOrderStatus(OrderStatus.OPEN);
        userOrder.setOpenTime(LocalDateTime.now());
        userOrderRepository.save(userOrder);
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