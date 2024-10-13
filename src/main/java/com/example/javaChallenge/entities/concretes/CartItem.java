package com.example.javaChallenge.entities.concretes;

import com.example.javaChallenge.dtos.CartDTO;
import com.example.javaChallenge.dtos.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "cartItem")
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartItem_id")
    private int id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_at_the_time")
    private int priceAtTheTime;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    public void increaseQuantity(int amount){
        this.quantity += amount;
    }

    public void decreaseQuantity(int amount){
        if (this.quantity - amount <= 0){
            this.quantity = 0;
        } else {
            this.quantity -= amount;
        }
    }

}
