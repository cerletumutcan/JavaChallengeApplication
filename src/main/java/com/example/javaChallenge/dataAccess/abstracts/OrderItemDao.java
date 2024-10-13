package com.example.javaChallenge.dataAccess.abstracts;

import com.example.javaChallenge.entities.concretes.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemDao extends JpaRepository<OrderItem, Integer> {

}
