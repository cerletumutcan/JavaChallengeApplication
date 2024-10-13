package com.example.javaChallenge.api.controllers;

import com.example.javaChallenge.business.abstracts.CustomerService;
import com.example.javaChallenge.core.utilities.results.DataResult;
import com.example.javaChallenge.core.utilities.results.Result;
import com.example.javaChallenge.dtos.CustomerDTO;
import com.example.javaChallenge.entities.concretes.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/customers")
public class CustomersController {

    private CustomerService customerService;

    @Autowired
    public CustomersController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping(value = "/addCustomer")
    public ResponseEntity<Result> addCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(customerService.addCustomer(customerDTO));
    }

    @GetMapping(value = "/findByEmail")
    public ResponseEntity<DataResult<Customer>> findByEmail(@RequestParam String email){
        return ResponseEntity.ok(customerService.findByEmail(email));
    }


}
