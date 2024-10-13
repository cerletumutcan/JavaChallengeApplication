package com.example.javaChallenge.dataAccess.abstracts;

import com.example.javaChallenge.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
    Optional<Product>findById(int id);
}
