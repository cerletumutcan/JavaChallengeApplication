package com.example.javaChallenge.dtos;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
public class OrderDTO implements Serializable {
    private int id;
    private String code;
    private LocalDateTime orderDate;
    private double orderTotalPrice;
    private CustomerDTO customerDTO;
    private List<OrderItemDTO> orderItemDTOList;
}
