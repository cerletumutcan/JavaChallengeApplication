package com.example.javaChallenge.business.concretes;

import com.example.javaChallenge.business.abstracts.CartService;
import com.example.javaChallenge.business.abstracts.CustomerService;
import com.example.javaChallenge.core.utilities.results.*;
import com.example.javaChallenge.dataAccess.abstracts.CustomerDao;
import com.example.javaChallenge.dtos.CustomerDTO;
import com.example.javaChallenge.entities.concretes.Customer;
import com.example.javaChallenge.mappers.CustomerMapperImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerManager implements CustomerService {

    private CustomerDao customerDao;
    private CartService cartService;

    @Autowired
    public CustomerManager(CustomerDao customerDao, CartService cartService){
        this.customerDao = customerDao;
        this.cartService = cartService;
    }

    @Transactional
    @Override
    public Result addCustomer(CustomerDTO customerDTO){
        Customer customer = CustomerMapperImpl.toEntity(customerDTO);
        Customer savedCustomer = customerDao.save(customer);
        cartService.createForCustomer(savedCustomer);
        return new SuccessResult("Kullanıcı Eklendi...");
    }

    @Override
    public DataResult<Customer> findByEmail(String email){
        Customer customer = customerDao.findByEmail(email);
        if (customer != null){
            return new SuccessDataResult<Customer>(customer, "Kullanıcı Bulundu...");
        } else {
            return new ErrorDataResult<Customer>("Kullanıcı Bulunamadı...");
        }
    }
}
