package com.example.javaChallenge.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int id;

    @Column(name = "total_price")
    private double totalPrice;

    @OneToOne(mappedBy = "cart")
    private Customer customer;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    public double calculateTotalPrice(){
        return cartItems.stream()
                .mapToDouble(cartItem -> cartItem.getQuantity() * cartItem.getProduct().getUnitPrice())
                .sum();
    }

    public void updateTotalPrice(){
        this.totalPrice = calculateTotalPrice();
    }
}
