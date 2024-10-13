package com.example.javaChallenge.business.abstracts;

import com.example.javaChallenge.core.utilities.results.DataResult;
import com.example.javaChallenge.core.utilities.results.Result;
import com.example.javaChallenge.entities.concretes.Cart;
import com.example.javaChallenge.entities.concretes.Customer;

public interface CartService {
    Result createForCustomer(Customer customer);
    Result calculateTotalAmount(Cart cart);
    DataResult<Cart> getCartByCustomerId(int customerId);
    Result deleteCart(int id);
    Result clearCartAfterOrder(int id);
}
