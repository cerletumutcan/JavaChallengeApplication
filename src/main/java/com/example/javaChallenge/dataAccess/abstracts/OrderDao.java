package com.example.javaChallenge.dataAccess.abstracts;

import com.example.javaChallenge.entities.concretes.Customer;
import com.example.javaChallenge.entities.concretes.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer> {
    Order findByCode(String code);
    List<Order> findOrderByCustomer(Customer customer);
}
