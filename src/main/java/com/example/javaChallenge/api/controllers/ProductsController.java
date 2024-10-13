package com.example.javaChallenge.api.controllers;

import com.example.javaChallenge.business.abstracts.ProductService;
import com.example.javaChallenge.core.utilities.results.DataResult;
import com.example.javaChallenge.core.utilities.results.Result;
import com.example.javaChallenge.dtos.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/products")
public class ProductsController {

    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping(value = "/addProduct")
    public ResponseEntity<Result> addProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.addProduct(productDTO));
    }

    @GetMapping(value = "/getAllProducts")
    public ResponseEntity<DataResult> getProduct(){
        return ResponseEntity.ok(productService.getProduct());
    }

    @PostMapping(value = "/updateProduct")
    public ResponseEntity<Result> updateProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.updateProduct(productDTO));
    }

    @GetMapping(value = "/deleteProduct/{id}")
    public ResponseEntity<Result> deleteProduct(@PathVariable("id") int id){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }
}
