package com.example.javaChallenge.business.concretes;

import com.example.javaChallenge.business.abstracts.CartItemService;
import com.example.javaChallenge.business.abstracts.CartService;
import com.example.javaChallenge.core.utilities.results.ErrorResult;
import com.example.javaChallenge.core.utilities.results.Result;
import com.example.javaChallenge.core.utilities.results.SuccessResult;
import com.example.javaChallenge.dataAccess.abstracts.CartDao;
import com.example.javaChallenge.dataAccess.abstracts.CartItemDao;
import com.example.javaChallenge.dataAccess.abstracts.ProductDao;
import com.example.javaChallenge.dtos.CartItemDTO;
import com.example.javaChallenge.entities.concretes.Cart;
import com.example.javaChallenge.entities.concretes.CartItem;
import com.example.javaChallenge.entities.concretes.Product;
import com.example.javaChallenge.mappers.CartItemMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemManager implements CartItemService {

    private CartItemDao cartItemDao;
    private CartService cartService;
    private ProductDao productDao;
    private CartDao cartDao;


    @Autowired
    public CartItemManager(
            CartItemDao cartItemDao, CartService cartService,
            ProductDao productDao, CartDao cartDao){
        super();
        this.cartItemDao = cartItemDao;
        this.cartService = cartService;
        this.productDao = productDao;
        this.cartDao = cartDao;
    }

    @Override
    public Result addItemToCart(CartItemDTO cartItemDTO) {
        Product product = productDao.findById(cartItemDTO.getProductId()).orElse(null);
        Cart cart = cartDao.findById(cartItemDTO.getCartId()).orElse(null);

        if (product == null){
            return new ErrorResult("Yeterli ürün bulunamadı...");
        }

        if (cart == null){
            return new ErrorResult("Sepet bulunamadı...");
        }

        CartItem cartItem = CartItemMapperImpl.toEntity(cartItemDTO, cart, product);

        if (product.getUnitsInStock() < cartItem.getQuantity()){
            return new ErrorResult("Yeterli ürün bulunamadı...");
        }

        this.cartItemDao.save(cartItem);
        cartService.calculateTotalAmount(cart);
        return new SuccessResult("Ürün sepete başarıyla eklendi...");
    }

    @Override
    public Result removeItemsFromCart(int id) {
        CartItem cartItem = cartItemDao.findById(id).orElse(null);
        if (cartItem != null){
            cartItemDao.deleteById(id);

            cartService.calculateTotalAmount(cartItem.getCart());

            return new SuccessResult("Ürün sepetten başarıyla silindi...");
        }
        return new ErrorResult("Ürün sepetten silinemedi...");
    }

    @Override
    public Result increaseQuantity(int id, int quantity) {
        CartItem cartItem = cartItemDao.findById(id).orElse(null);
        if (cartItem == null){
            return new ErrorResult("Sepette ürün bulunamadı...");
        }
        if (cartItem.getProduct().getUnitsInStock() < cartItem.getQuantity() + quantity){
            return new ErrorResult("Yeteri kadar ürün bulunamadı...");
        }
        cartItem.increaseQuantity(quantity);
        cartItemDao.save(cartItem);

        cartService.calculateTotalAmount(cartItem.getCart());

        return new SuccessResult("Ürün miktarı sepette başarıyla arttırıldı...");
    }

    @Override
    public Result decreaseQuantity(int id, int quantity) {
        CartItem cartItem = cartItemDao.findById(id).orElse(null);
        if (cartItem == null){
            return new ErrorResult("Sepette ürün bulunamadı...");
        }

        cartItem.decreaseQuantity(quantity);

        if (cartItem.getQuantity() == 0){
            cartItemDao.delete(cartItem);
            cartService.calculateTotalAmount(cartItem.getCart());
            return new SuccessResult("Ürün sepetten kaldırıldı...");
        }
        cartItemDao.save(cartItem);

        cartService.calculateTotalAmount(cartItem.getCart());

        return new SuccessResult("Ürün miktarı başarıyla azaltıldı...");
    }

}
