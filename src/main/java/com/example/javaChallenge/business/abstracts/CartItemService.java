package com.example.javaChallenge.business.abstracts;

import com.example.javaChallenge.core.utilities.results.Result;
import com.example.javaChallenge.dtos.CartItemDTO;

public interface CartItemService {
    Result addItemToCart(CartItemDTO cartItemDTO);
    Result removeItemsFromCart(int id);
    Result increaseQuantity(int id, int quantity);
    Result decreaseQuantity(int id, int quantity);
}
