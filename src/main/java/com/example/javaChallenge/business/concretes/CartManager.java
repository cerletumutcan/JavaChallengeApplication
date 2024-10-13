package com.example.javaChallenge.business.concretes;

import com.example.javaChallenge.business.abstracts.CartService;
import com.example.javaChallenge.core.utilities.results.*;
import com.example.javaChallenge.dataAccess.abstracts.CartDao;
import com.example.javaChallenge.dataAccess.abstracts.CartItemDao;
import com.example.javaChallenge.entities.concretes.Cart;
import com.example.javaChallenge.entities.concretes.CartItem;
import com.example.javaChallenge.entities.concretes.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartManager implements CartService {

    private CartDao cartDao;
    private CartItemDao cartItemDao;

    @Autowired
    public CartManager(CartDao cartDao, CartItemDao cartItemDao){
        this.cartDao = cartDao;
        this.cartItemDao = cartItemDao;
    }

    @Override
    public Result createForCustomer(Customer customer) {
        Cart cart = new Cart();
        cart.setCustomer(customer);
        customer.setCart(cart);
        cartDao.save(cart);
        return new SuccessResult("Kişisel sepetiniz başarıyla oluşturuldu...");
    }

    @Override
    public Result calculateTotalAmount(Cart cart) {
        cart.updateTotalPrice();
        cartDao.save(cart);
        return new SuccessResult("Sepetin toplam tutarı başarıyla hesaplandı...");
    }

    @Override
    public DataResult<Cart> getCartByCustomerId(int customerId) {
        Cart cart = cartDao.findByCustomer_Id(customerId);
        if (cart == null){
            return new ErrorDataResult<>("Sepet Bulunamadı...");
        }
        return new SuccessDataResult<>(cart, "Sepet başarıyla getirildi...");
    }

    @Override
    public Result deleteCart(int id) {
        cartDao.deleteById(id);
        return new SuccessResult("Sepet başarıyla silindi...");
    }

    @Override
    public Result clearCartAfterOrder(int id) {
        List<CartItem> cartItems = cartItemDao.findByCart_Id(id);
        if (cartItems != null && !cartItems.isEmpty()){
            cartItemDao.deleteAll(cartItems);
        }
        Cart cart = cartDao.findById(id).orElse(null);
        if (cart != null){
            cart.setTotalPrice(0);
            cartDao.save(cart);
        }
        return new SuccessResult("Sepet ve içindeki ürünler başarıyla silindi...");
    }


}
