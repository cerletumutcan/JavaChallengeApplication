package com.example.javaChallenge.dtos;

import com.example.javaChallenge.entities.concretes.CartItem;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class CartDTO implements Serializable {

    private int id;
    private double totalPrice;
    private CustomerDTO customerDTO;
    private List<CartItemDTO> cartItemDTOList;
}
