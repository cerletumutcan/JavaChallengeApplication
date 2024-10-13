package com.example.javaChallenge.business.abstracts;

import com.example.javaChallenge.core.utilities.results.DataResult;
import com.example.javaChallenge.core.utilities.results.Result;
import com.example.javaChallenge.dtos.CustomerDTO;
import com.example.javaChallenge.entities.concretes.Customer;
import com.example.javaChallenge.entities.concretes.Order;

import java.util.List;

public interface OrderService {
    Result createOrderForCustomer(CustomerDTO customerDTO);
    DataResult<Order> getOrderForCode(String code);
    DataResult<List<Order>> getAllOrdersForCustomer(CustomerDTO customerDTO);
}
