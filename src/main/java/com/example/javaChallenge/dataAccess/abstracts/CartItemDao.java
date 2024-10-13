package com.example.javaChallenge.dataAccess.abstracts;

import com.example.javaChallenge.entities.concretes.Cart;
import com.example.javaChallenge.entities.concretes.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemDao extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByCart_Id(int cartId);
}
