package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Wallet;
import com.example.demo.model.User;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findByUser(User user);
}