package com.example.javaChallenge.business.abstracts;

import com.example.javaChallenge.core.utilities.results.Result;
import com.example.javaChallenge.dtos.OrderItemDTO;
import com.example.javaChallenge.entities.concretes.Order;

public interface OrderItemService {
    Result addItemToOrder(OrderItemDTO orderItemDTO, Order order);
}
