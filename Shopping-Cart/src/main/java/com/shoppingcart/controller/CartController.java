package com.shoppingcart.controller;

import com.shoppingcart.dao.JdbcCartItemDao;
import com.shoppingcart.model.CartItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final JdbcCartItemDao jdbcCartItemDao;

    public CartController(JdbcCartItemDao jdbcCartItemDao) {
        this.jdbcCartItemDao = jdbcCartItemDao;
    }

    // JUST TESTING THIS
    @GetMapping
    public List<CartItem> list() {
        return jdbcCartItemDao.getCartItems();
    }

}
