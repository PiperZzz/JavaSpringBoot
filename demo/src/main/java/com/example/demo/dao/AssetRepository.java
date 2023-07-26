package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Asset;
import com.example.demo.model.Wallet;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findBySymbol(Wallet wallet);
}