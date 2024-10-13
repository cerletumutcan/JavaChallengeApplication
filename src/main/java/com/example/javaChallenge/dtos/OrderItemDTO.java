package com.example.javaChallenge.dtos;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class OrderItemDTO implements Serializable {
    private int id;
    private int quantity;
    private double unitPriceAtPurchase;
    private double totalPrice;
    private int orderId;
    private int productId;
}
