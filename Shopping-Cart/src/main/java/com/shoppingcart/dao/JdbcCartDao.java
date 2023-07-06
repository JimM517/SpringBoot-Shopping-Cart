package com.shoppingcart.dao;

import com.shoppingcart.model.CartItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class JdbcCartDao implements CartDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCartDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public BigDecimal getCartSubtotal(int userId) {
        String sql = "SELECT SUM(product.price * cart_item.quantity) AS subtotal FROM product " +
                    "JOIN cart_item ON cart_item.product_id = product.product_id " +
                    "JOIN users ON users.user_id = cart_item.user_id " +
                    "WHERE users.user_id = ?";
        BigDecimal results = jdbcTemplate.queryForObject(sql, BigDecimal.class, userId);
        return results;
    }

    //MAY NOT NEED THIS HERE, CAN PROBABLY MOVE TO SERVICE CLASS
    @Override
    public BigDecimal getTax(int id) {
        return null;
    }

    @Override
    public BigDecimal getCartTotal(int id) {
        return null;
    }

    @Override
    public List<CartItem> displayCartItems(int id) {
        return null;
    }
}
