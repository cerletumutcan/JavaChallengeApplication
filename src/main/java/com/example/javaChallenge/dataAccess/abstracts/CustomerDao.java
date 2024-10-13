package com.example.javaChallenge.dataAccess.abstracts;

import com.example.javaChallenge.entities.concretes.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
    Customer findByEmail(String email);
}
