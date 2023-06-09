package com.shoppingcart.dao;

import com.shoppingcart.model.CartItem;
import com.shoppingcart.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCartItemDao implements CartItemDao{

    private final JdbcTemplate jdbcTemplate;
    private final ProductDao productDao;

    public JdbcCartItemDao(JdbcTemplate jdbcTemplate, ProductDao productDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.productDao = productDao;
    }


//    String sql = "SELECT SUM(price * quantity) AS cart_total FROM product\n" +
//            "JOIN cart_item on cart_item.product_id = product.product_id\n" +
//            "JOIN users ON users.user_id = cart_item.user_id\n" +
//            "WHERE users.user_id = 1";
//
//    String anotherSQL = "SELECT product.name, product.price, cart_item.quantity, SUM(product.price * cart_item.quantity) FROM cart_item\n" +
//            "JOIN product ON product.product_id = cart_item.product_id\n" +
//            "JOIN users ON users.user_id = cart_item.user_id\n" +
//            "WHERE cart_item.user_id = 1\n" +
//            "GROUP BY product.name, product.price, cart_item.quantity";


    @Override
    public List<CartItem> getCartItems() {
        List<CartItem> cartItems = new ArrayList<>();
        String sql = "SELECT * FROM cart_item";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            CartItem cartItem = mapRowToCartItem(results);
            cartItems.add(cartItem);
        }
        return cartItems;
    }

    @Override
    public List<CartItem> getProductsById(int productId) {
        return null;
    }

    @Override
    public List<CartItem> getByUserId(int userId) {
        return null;
    }

    @Override
    public List<Product> getProductsByUserId(int userId) {
        return null;
    }

    @Override
    public void deleteCartItem(int cartId) {

    }


    private CartItem mapRowToCartItem(SqlRowSet results) {
        CartItem cartItem = new CartItem();
        cartItem.setCartId(results.getInt("cart_item_id"));
        cartItem.setUserId(results.getInt("user_id"));
        cartItem.setProductId(results.getInt("product_id"));
        cartItem.setQuantity(results.getInt("quantity"));
        cartItem.setProduct(productDao.productById(results.getInt("product_id")));
        return cartItem;
    }

}
