package com.example.javaChallenge.api.controllers;

import com.example.javaChallenge.business.abstracts.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/cart")
public class CartsController {

    private CartService cartService;

    @Autowired
    public CartsController(CartService cartService){
        this.cartService = cartService;
    }

}
