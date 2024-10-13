package com.example.javaChallenge.dtos;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ProductDTO implements Serializable {
    private int id;
    private String productName;
    private String productCode;
    private String productDescription;
    private double unitPrice;
    private int unitsInStock;
}
