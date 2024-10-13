package com.example.javaChallenge.business.abstracts;

import com.example.javaChallenge.core.utilities.results.DataResult;
import com.example.javaChallenge.core.utilities.results.Result;
import com.example.javaChallenge.dtos.CustomerDTO;
import com.example.javaChallenge.entities.concretes.Customer;

public interface CustomerService {
    Result addCustomer(CustomerDTO customerDTO);
    DataResult<Customer> findByEmail(String email);
}
