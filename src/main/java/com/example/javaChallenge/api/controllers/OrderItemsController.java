package com.example.javaChallenge.api.controllers;

import com.example.javaChallenge.business.abstracts.OrderItemService;
import com.example.javaChallenge.core.utilities.results.Result;
import com.example.javaChallenge.dtos.OrderItemDTO;
import com.example.javaChallenge.entities.concretes.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/orderItems")
public class OrderItemsController {

    private OrderItemService orderItemService;

    @Autowired
    public OrderItemsController(OrderItemService orderItemService){
        this.orderItemService = orderItemService;
    }

    @PostMapping(value = "/addOrderItem")
    public ResponseEntity<Result> addItemToOrder(@RequestBody OrderItemDTO orderItemDTO, @RequestBody Order order){
        return ResponseEntity.ok(orderItemService.addItemToOrder(orderItemDTO, order));
    }
}
