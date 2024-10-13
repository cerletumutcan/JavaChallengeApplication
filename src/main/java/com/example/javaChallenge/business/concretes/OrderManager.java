package com.example.javaChallenge.business.concretes;

import com.example.javaChallenge.business.abstracts.*;
import com.example.javaChallenge.core.utilities.results.DataResult;
import com.example.javaChallenge.core.utilities.results.ErrorResult;
import com.example.javaChallenge.core.utilities.results.Result;
import com.example.javaChallenge.core.utilities.results.SuccessResult;
import com.example.javaChallenge.dataAccess.abstracts.OrderDao;
import com.example.javaChallenge.dtos.CustomerDTO;
import com.example.javaChallenge.dtos.OrderItemDTO;
import com.example.javaChallenge.entities.concretes.*;
import com.example.javaChallenge.mappers.CustomerMapperImpl;
import com.example.javaChallenge.mappers.ProductMapperImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderManager implements OrderService {

    private OrderDao orderDao;
    private OrderItemManager orderItemManager;
    private CartService cartService;
    private OrderItemService orderItemService;
    private ProductService productService;
    private CartItemService cartItemService;

    @Autowired
    public OrderManager(
            OrderDao orderDao,
            OrderItemManager orderItemManager,
            OrderItemService orderItemService,
            CartService cartService,
            ProductService productService,
            CartItemService cartItemService){

        this.orderDao = orderDao;
        this.orderItemManager = orderItemManager;
        this.orderItemService = orderItemService;
        this.cartService = cartService;
        this.productService = productService;
        this.cartItemService = cartItemService;
    }

    @Override
    @Transactional
    public Result createOrderForCustomer(CustomerDTO customerDTO) {

        Customer customer = CustomerMapperImpl.toEntity(customerDTO);
        DataResult<Cart> cartDataResult = cartService.getCartByCustomerId(customer.getId());

        if (!cartDataResult.isSuccess()){
            return new ErrorResult(cartDataResult.getMessage());
        }
        Cart cart = cartDataResult.getData();

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderTotalPrice(0);
        String orderCode = UUID.randomUUID().toString();
        order.setCode(orderCode);
        orderDao.save(order);

        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems){
            Product product = cartItem.getProduct();

            Result stockUpdateResult = productService.updateStock(product.getId(), cartItem.getQuantity());
            if (!stockUpdateResult.isSuccess()){
                return new ErrorResult(stockUpdateResult.getMessage());
            }
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setProductId(cartItem.getProduct().getId());
            orderItemDTO.setQuantity(cartItem.getQuantity());
            orderItemService.addItemToOrder(orderItemDTO,order);
        }

        cartService.clearCartAfterOrder(cart.getId());
        orderDao.save(order);

        return new SuccessResult("Sipariş başarıyla oluşturuldu...");
    }

    @Override
    public DataResult<Order> getOrderForCode(String code) {
        Order order = orderDao.findByCode(code);
        return new DataResult<>(order,true);
    }

    @Override
    public DataResult<List<Order>> getAllOrdersForCustomer(CustomerDTO customerDTO) {
        List<Order> orders = orderDao.findOrderByCustomer(CustomerMapperImpl.toEntity(customerDTO));
        return new DataResult<>(orders,true);
    }

}














