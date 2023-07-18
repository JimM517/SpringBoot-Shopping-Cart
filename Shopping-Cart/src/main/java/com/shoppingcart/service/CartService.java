package com.shoppingcart.service;

import com.shoppingcart.dao.CartItemDao;
import com.shoppingcart.dao.ProductDao;
import com.shoppingcart.dao.UserDao;
import com.shoppingcart.model.Cart;
import com.shoppingcart.model.User;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class CartService {

    private CartItemDao cartItemDao;
    private ProductDao productDao;
    private UserDao userDao;

    private TaxService taxService;


    public CartService(CartItemDao cartItemDao, ProductDao productDao, UserDao userDao, TaxService taxService) {
        this.cartItemDao = cartItemDao;
        this.productDao = productDao;
        this.userDao = userDao;
        this.taxService = taxService;
    }


    //Get current user
    public User getUser(Principal principal) {
        String user = principal.getName();
        return userDao.findByUsername(user);
    }

    public Cart getCart(Principal principal) {
        User user = getUser(principal);
        int userId = user.getUserId();
        return new Cart();
    }







}
