package com.shoppingcart.dao;

import com.shoppingcart.model.CartItem;
import com.shoppingcart.model.Product;

import java.util.List;
import java.util.Map;

public interface CartItemDao {

    List<CartItem> getCartItems();

    List<CartItem> getProductsById(int productId);

    List<Map<String, Object>> getByUserId(int userId);

    List<Product> getProductsByUserId(int userId);

    void deleteCartItem(int cartId);


}
