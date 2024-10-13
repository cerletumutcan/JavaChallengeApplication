package com.example.javaChallenge.mappers;

import com.example.javaChallenge.dtos.CustomerDTO;
import com.example.javaChallenge.entities.concretes.Customer;

import java.io.Serializable;

public class CustomerMapperImpl implements Serializable {

    public static CustomerDTO toDTO(Customer customer){
        CustomerDTO result = new CustomerDTO();
        result.setId(customer.getId());
        result.setEmail(customer.getEmail());
        result.setPassword(customer.getPassword());
        result.setPhoneNumber(customer.getPhoneNumber());

        return result;
    }

    public static Customer toEntity(CustomerDTO customerDTO){
        Customer result = new Customer();
        result.setId(customerDTO.getId());
        result.setEmail(customerDTO.getEmail());
        result.setPassword(customerDTO.getPassword());
        result.setPhoneNumber(customerDTO.getPhoneNumber());

        return result;
    }
}
