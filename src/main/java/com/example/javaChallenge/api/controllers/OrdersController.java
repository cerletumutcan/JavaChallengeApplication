package com.example.javaChallenge.api.controllers;

import com.example.javaChallenge.business.abstracts.OrderService;
import com.example.javaChallenge.core.utilities.results.DataResult;
import com.example.javaChallenge.core.utilities.results.Result;
import com.example.javaChallenge.dtos.CustomerDTO;
import com.example.javaChallenge.dtos.OrderDTO;
import com.example.javaChallenge.entities.concretes.Customer;
import com.example.javaChallenge.entities.concretes.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/orders")
public class OrdersController {

    private OrderService orderService;

    @Autowired
    public OrdersController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping(value = "/createOrderByCustomer")
    public ResponseEntity<Result> createOrder(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(orderService.createOrderForCustomer(customerDTO));
    }

    @GetMapping(value = "/getOrderForCode")
    public ResponseEntity<DataResult> getOrderForCode(@RequestParam("code") String code){
        return ResponseEntity.ok(orderService.getOrderForCode(code));
    }

    @PostMapping(value = "/getAllOrdersForCustomer")
    public ResponseEntity<DataResult<List<Order>>> getAllOrdersForCustomer(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(orderService.getAllOrdersForCustomer(customerDTO));
    }


}
