package com.example.demo.repository;

import com.example.demo.model.Wallet;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findByUserAndCryptocurrency(User user, String cryptocurrency);
}