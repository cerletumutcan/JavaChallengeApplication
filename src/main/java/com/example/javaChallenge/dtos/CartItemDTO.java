package com.example.javaChallenge.dtos;

import com.example.javaChallenge.entities.concretes.Cart;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CartItemDTO implements Serializable {
    private int id;
    private int quantity;
    private int priceAtTheTime;
    private int cartId;
    private int productId;
}
