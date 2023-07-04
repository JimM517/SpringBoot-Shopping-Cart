package com.shoppingcart.controller;

import com.shoppingcart.dao.JdbcCartItemDao;
import com.shoppingcart.model.CartItem;
import com.shoppingcart.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // JUST TESTING THIS -> THIS WORKS
    @GetMapping
    public List<CartItem> list() {
        return jdbcCartItemDao.getCartItems();
    }

    //WORKS
//    @GetMapping("/{id}")
//    public List<Map<String, Object>> getProductsByUserId(@PathVariable int id) {
//        return jdbcCartItemDao.getByUserId(id);
//    }

        @GetMapping("/{id}")
        public List<Product> getProductsByUserId(@PathVariable int id) {
        return jdbcCartItemDao.getProductsByUserId(id);
    }


}
