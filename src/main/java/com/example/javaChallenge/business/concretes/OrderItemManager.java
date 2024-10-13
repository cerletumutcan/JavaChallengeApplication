package com.example.javaChallenge.business.concretes;

import com.example.javaChallenge.business.abstracts.OrderItemService;
import com.example.javaChallenge.business.abstracts.OrderService;
import com.example.javaChallenge.core.utilities.results.ErrorResult;
import com.example.javaChallenge.core.utilities.results.Result;
import com.example.javaChallenge.core.utilities.results.SuccessResult;
import com.example.javaChallenge.dataAccess.abstracts.OrderDao;
import com.example.javaChallenge.dataAccess.abstracts.OrderItemDao;
import com.example.javaChallenge.dataAccess.abstracts.ProductDao;
import com.example.javaChallenge.dtos.OrderItemDTO;
import com.example.javaChallenge.entities.concretes.Order;
import com.example.javaChallenge.entities.concretes.OrderItem;
import com.example.javaChallenge.entities.concretes.Product;
import com.example.javaChallenge.mappers.OrderItemMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemManager implements OrderItemService {

    private OrderItemDao orderItemDao;
    private ProductDao productDao;
    private OrderDao orderDao;

    @Autowired
    public OrderItemManager(
            OrderItemDao orderItemDao,
            ProductDao productDao,
            OrderDao orderDao){
        super();
        this.orderItemDao = orderItemDao;
        this.productDao = productDao;
        this.orderDao = orderDao;

    }

    @Override
    public Result addItemToOrder(OrderItemDTO orderItemDTO, Order order) {

        Product product = productDao.findById(orderItemDTO.getProductId()).orElse(null);
        if (product == null){
            return new ErrorResult("Ürün bulunamadı...");
        }

        OrderItem orderItem = OrderItemMapperImpl.toEntity(orderItemDTO, order, product);
        orderItem.calculateTotalPrice();
        orderItemDao.save(orderItem);

        order.setOrderTotalPrice(order.getOrderTotalPrice() + orderItem.getTotalPrice());
        orderDao.save(order);

        return new SuccessResult("Ürün siparişe eklendi...");
    }
}
