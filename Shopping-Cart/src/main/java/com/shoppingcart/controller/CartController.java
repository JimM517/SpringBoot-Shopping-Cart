package com.shoppingcart.controller;

import com.shoppingcart.model.Cart;
import com.shoppingcart.service.CartService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // JUST TESTING THIS -> THIS WORKS
//    @GetMapping
//    public List<CartItem> list() {
//        return jdbcCartItemDao.getCartItems();
//    }

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

//    @GetMapping("/{id}")
//    public BigDecimal getUserTotal(@PathVariable int id) {
//        return jdbcCartDao.getCartSubtotal(id);
//    }

    @GetMapping()
    @PreAuthorize("permitAll()")
    public Cart getUsersCart(Principal principal) {
        return cartService.getCart(principal);
    }


}
