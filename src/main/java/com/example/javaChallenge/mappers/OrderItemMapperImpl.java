package com.example.javaChallenge.mappers;

import com.example.javaChallenge.dtos.OrderItemDTO;
import com.example.javaChallenge.entities.concretes.Order;
import com.example.javaChallenge.entities.concretes.OrderItem;
import com.example.javaChallenge.entities.concretes.Product;

import java.io.Serializable;

public class OrderItemMapperImpl implements Serializable {

    public static OrderItemDTO toDTO(OrderItem orderItem){
        OrderItemDTO result = new OrderItemDTO();
        result.setId(orderItem.getId());
        result.setQuantity(orderItem.getQuantity());
        result.setUnitPriceAtPurchase(orderItem.getUnitPriceAtPurchase());
        result.setTotalPrice(orderItem.getTotalPrice());
        result.setProductId(orderItem.getProduct().getId());
        result.setOrderId(orderItem.getOrder().getId());

        return result;
    }

    public static OrderItem toEntity(OrderItemDTO orderItemDTO, Order order, Product product){
        OrderItem result = new OrderItem();
        result.setId(orderItemDTO.getId());
        result.setOrder(order);
        result.setProduct(product);
        result.setUnitPriceAtPurchase(product.getUnitPrice());
        result.setTotalPrice(orderItemDTO.getTotalPrice());
        result.setQuantity(orderItemDTO.getQuantity());

        return result;
    }
}
