package com.example.javaChallenge.mappers;

import com.example.javaChallenge.dataAccess.abstracts.ProductDao;
import com.example.javaChallenge.dtos.OrderDTO;
import com.example.javaChallenge.entities.concretes.Order;
import com.example.javaChallenge.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.stream.Collectors;


public class OrderMapperImpl implements Serializable {

    private static ProductDao productDao;

    @Autowired
    public OrderMapperImpl(ProductDao productDao){
        OrderMapperImpl.productDao = productDao;
    }

    public static OrderDTO toDTO(Order order){
        OrderDTO result = new OrderDTO();
        result.setId(order.getId());
        result.setCode(order.getCode());
        result.setOrderDate(order.getOrderDate());
        result.setOrderTotalPrice(order.getOrderTotalPrice());
        result.setCustomerDTO(CustomerMapperImpl.toDTO(order.getCustomer()));
        result.setOrderItemDTOList(order.getOrderItems().stream()
                .map(OrderItemMapperImpl::toDTO)
                .collect(Collectors.toList())
        );
        return result;
    }

    public static Order toEntity(OrderDTO orderDTO){
        Order result = new Order();
        result.setId(orderDTO.getId());
        result.setCode(orderDTO.getCode());
        result.setOrderDate(orderDTO.getOrderDate());
        result.setOrderTotalPrice(orderDTO.getOrderTotalPrice());
        result.setCustomer(CustomerMapperImpl.toEntity(orderDTO.getCustomerDTO()));
        result.setOrderItems(orderDTO.getOrderItemDTOList().stream()
                .map(orderItemDTO -> {
                    Product product = productDao.findById(orderItemDTO.getProductId())
                            .orElseThrow(() -> new RuntimeException("Product not found"));
                    return OrderItemMapperImpl.toEntity(orderItemDTO, result, product);
                })
                .collect(Collectors.toList())
        );
        return result;
    }
}
