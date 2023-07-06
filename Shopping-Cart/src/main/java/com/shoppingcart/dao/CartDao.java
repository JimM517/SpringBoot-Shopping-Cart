package com.shoppingcart.dao;

import com.shoppingcart.model.CartItem;

import java.math.BigDecimal;
import java.util.List;

public interface CartDao {

    BigDecimal getCartSubtotal(int userId);

    BigDecimal getTax(int id);

    BigDecimal getCartTotal(int id);

    List<CartItem> displayCartItems(int id);


}
