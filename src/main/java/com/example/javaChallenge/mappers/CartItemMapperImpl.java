package com.example.javaChallenge.mappers;

import com.example.javaChallenge.dtos.CartItemDTO;
import com.example.javaChallenge.entities.concretes.Cart;
import com.example.javaChallenge.entities.concretes.CartItem;
import com.example.javaChallenge.entities.concretes.Product;

import java.io.Serializable;

public class CartItemMapperImpl implements Serializable {
    public static CartItemDTO toDTO(CartItem cartItem){
        CartItemDTO result = new CartItemDTO();
        result.setId(cartItem.getId());
        result.setQuantity(cartItem.getQuantity());
        result.setPriceAtTheTime(cartItem.getPriceAtTheTime());
        result.setCartId(cartItem.getCart().getId());
        result.setProductId(cartItem.getProduct().getId());
        return result;
    }

    public static CartItem toEntity(CartItemDTO cartItemDTO, Cart cart, Product product){
        CartItem result = new CartItem();
        result.setId(cartItemDTO.getId());
        result.setQuantity(cartItemDTO.getQuantity());
        result.setPriceAtTheTime(cartItemDTO.getPriceAtTheTime());
        result.setCart(cart);
        result.setProduct(product);
        return result;
    }
}
