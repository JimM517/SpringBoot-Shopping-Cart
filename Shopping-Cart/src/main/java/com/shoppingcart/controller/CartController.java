package com.shoppingcart.controller;

import com.shoppingcart.dao.JdbcCartDao;
import com.shoppingcart.dao.JdbcCartItemDao;
import com.shoppingcart.model.CartItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final JdbcCartItemDao jdbcCartItemDao;
    private final JdbcCartDao jdbcCartDao;

    public CartController(JdbcCartItemDao jdbcCartItemDao, JdbcCartDao jdbcCartDao) {
        this.jdbcCartItemDao = jdbcCartItemDao;
        this.jdbcCartDao = jdbcCartDao;
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

    //WORKS
//        @GetMapping("/{id}")
//        public List<Product> getProductsByUserId(@PathVariable int id) {
//        return jdbcCartItemDao.getProductsByUserId(id);
//    }

    @GetMapping("/{id}")
    public BigDecimal getUserTotal(@PathVariable int id) {
        return jdbcCartDao.getCartSubtotal(id);
    }


}
