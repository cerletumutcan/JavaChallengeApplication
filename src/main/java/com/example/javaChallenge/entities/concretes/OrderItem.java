package com.example.javaChallenge.entities.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "order_items")
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private int id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unit_price_at_purchase")
    private double unitPriceAtPurchase;

    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public double calculateTotalPrice(){
        double totalPrice = this.quantity * this.unitPriceAtPurchase;
        this.setTotalPrice(totalPrice);
        return totalPrice;
    }

}
