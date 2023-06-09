package com.shoppingcart.dao;

import com.shoppingcart.model.CartItem;
import com.shoppingcart.model.Product;

import java.util.List;

public interface CartItemDao {

    List<CartItem> getCartItems();

    List<CartItem> getProductsById(int productId);

    List<CartItem> getByUserId(int userId);

    List<Product> getProductsByUserId(int userId);

    void deleteCartItem(int cartId);


}
