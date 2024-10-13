package com.example.javaChallenge.mappers;

import com.example.javaChallenge.dataAccess.abstracts.ProductDao;
import com.example.javaChallenge.dtos.CartDTO;
import com.example.javaChallenge.entities.concretes.Cart;
import com.example.javaChallenge.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.stream.Collectors;

public class CartMapperImpl implements Serializable {

    private static ProductDao productDao;

    @Autowired
    public CartMapperImpl(ProductDao productDao){
        CartMapperImpl.productDao = productDao;
    }

    public static CartDTO toDTO(Cart cart){
        CartDTO result = new CartDTO();
        result.setId(cart.getId());
        result.setTotalPrice(cart.getTotalPrice());
        result.setCustomerDTO(CustomerMapperImpl.toDTO(cart.getCustomer()));
        result.setCartItemDTOList(cart.getCartItems().stream()
                .map(CartItemMapperImpl::toDTO)
                .collect(Collectors.toList())
        );
        return result;
    }

    public static Cart toEntity(CartDTO cartDTO){
        Cart result = new Cart();
        result.setId(cartDTO.getId());
        result.setTotalPrice(cartDTO.getTotalPrice());
        result.setCustomer(CustomerMapperImpl.toEntity(cartDTO.getCustomerDTO()));
        result.setCartItems(cartDTO.getCartItemDTOList().stream()
                .map(cartItemDTO -> {
                    Product product = productDao.findById(cartItemDTO.getId())
                            .orElseThrow(() -> new RuntimeException("Product not found"));
                    return CartItemMapperImpl.toEntity(cartItemDTO, result, product);
                })
                .collect(Collectors.toList())
        );
        return result;
    }
}


//public static Cart toEntity(CartDTO cartDTO){
//    Cart result = new Cart();
//    result.setId(cartDTO.getId());
//    result.setTotalPrice(cartDTO.getTotalPrice());
//    result.setCustomer(CustomerMapperImpl.toEntity(cartDTO.getCustomerDTO()));
//    result.setCartItems(cartDTO.getCartItemDTOList().stream()
//            .map(cartItemDTO -> {
//                Product product = productDao.findById(cartItemDTO.getId())
//                        .orElseThrow(() -> new RuntimeException("Product not found")); // Hata yönetimi
//                return CartItemMapperImpl.toEntity(cartItemDTO, result, product);
//            })
//            .collect(Collectors.toList())
//    );
//    return result;
//}