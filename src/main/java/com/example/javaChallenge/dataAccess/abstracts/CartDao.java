package com.example.javaChallenge.dataAccess.abstracts;


import com.example.javaChallenge.entities.concretes.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {
    Cart findByCustomer_Id(int customerId);
}
