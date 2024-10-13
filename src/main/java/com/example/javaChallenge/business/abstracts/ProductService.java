package com.example.javaChallenge.business.abstracts;

import com.example.javaChallenge.core.utilities.results.DataResult;
import com.example.javaChallenge.core.utilities.results.Result;
import com.example.javaChallenge.dtos.ProductDTO;

import java.util.List;

public interface ProductService {
    DataResult<List<ProductDTO>> getProduct();
    Result addProduct(ProductDTO productDTO);
    Result updateProduct(ProductDTO productDTO);
    Result deleteProduct(int id);
    Result updateStock(int id, int quantity);
}
