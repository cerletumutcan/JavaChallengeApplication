package com.example.javaChallenge.mappers;

import com.example.javaChallenge.dtos.ProductDTO;
import com.example.javaChallenge.entities.concretes.Product;

import java.io.Serializable;

public class ProductMapperImpl implements Serializable {

    public static ProductDTO toDTO(Product product){
        ProductDTO result = new ProductDTO();
        result.setId(product.getId());
        result.setProductName(product.getProductName());
        result.setProductCode(product.getProductCode());
        result.setProductDescription(product.getProductDescription());
        result.setUnitPrice(product.getUnitPrice());
        result.setUnitsInStock(product.getUnitsInStock());

        return result;
    }

    public static Product toEntity(ProductDTO productDTO){
        Product result = new Product();
        result.setId(productDTO.getId());
        result.setProductName(productDTO.getProductName());
        result.setProductCode(productDTO.getProductCode());
        result.setProductDescription(productDTO.getProductDescription());
        result.setUnitPrice(productDTO.getUnitPrice());
        result.setUnitsInStock(productDTO.getUnitsInStock());

        return result;
    }
}
