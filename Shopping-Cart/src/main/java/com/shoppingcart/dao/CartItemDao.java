package com.shoppingcart.dao;

import com.shoppingcart.model.CartItem;
import com.shoppingcart.model.Product;

import java.util.List;
import java.util.Map;

public interface CartItemDao {

    CartItem getById(int cartItemId, int userId);

    CartItem getByProdAndUserId(int productId, int userId);

    List<CartItem> getByUser(int userId);

    int addItem(CartItem item);

    void update(CartItem item);



    List<CartItem> getCartItems();

    List<CartItem> getProductsById(int productId);

    List<Map<String, Object>> getByUserId(int userId);

    List<Product> getProductsByUserId(int userId);

    void deleteCartItem(int cartId, int userId);

    void clearCartByUser(int userId);


}
