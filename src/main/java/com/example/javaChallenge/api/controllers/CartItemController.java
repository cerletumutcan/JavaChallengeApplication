package com.example.javaChallenge.api.controllers;

import com.example.javaChallenge.business.abstracts.CartItemService;
import com.example.javaChallenge.core.utilities.results.Result;
import com.example.javaChallenge.dtos.CartItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/cartItems")
public class CartItemController {

    private CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService){
        this.cartItemService = cartItemService;
    }

    @PostMapping(value = "/addCartItem")
    public ResponseEntity<Result> addItemToCart(@RequestBody CartItemDTO cartItemDTO){
        return ResponseEntity.ok(cartItemService.addItemToCart(cartItemDTO));
    }

    @GetMapping(value = "/removeItemsFromCart/{id}")
    public ResponseEntity<Result> removeItemsFromCart(@PathVariable("id") int id){
        return ResponseEntity.ok(cartItemService.removeItemsFromCart(id));
    }

    @PostMapping(value = "/increaseQuantity/{id}")
    public ResponseEntity<Result> increaseQuantity
            (@PathVariable("id") int id, @RequestParam("quantity") int quantity){
        return ResponseEntity.ok(cartItemService.increaseQuantity(id,quantity));
    }

    @PostMapping(value = "/decreaseQuantity/{id}")
    public ResponseEntity<Result> decreaseQuantity
            (@PathVariable("id") int id, @RequestParam("quantity") int quantity){
        return ResponseEntity.ok(cartItemService.decreaseQuantity(id,quantity));
    }

}
