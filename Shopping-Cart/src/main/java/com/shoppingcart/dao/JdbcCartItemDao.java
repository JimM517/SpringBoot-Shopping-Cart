package com.shoppingcart.dao;

import com.shoppingcart.model.CartItem;
import com.shoppingcart.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JdbcCartItemDao implements CartItemDao{

    private final JdbcTemplate jdbcTemplate;


    public JdbcCartItemDao(JdbcTemplate jdbcTemplate, ProductDao productDao) {
        this.jdbcTemplate = jdbcTemplate;

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
    public CartItem getById(int cartItemId, int userId) {
        CartItem item = null;
        String sql = "SELECT * FROM cart_item WHERE cart_item_id = ? AND user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, cartItemId, userId);
        while(results.next()) {
            item = mapRowToCartItem(results);
        }
        return item;
    }

    @Override
    public CartItem getByProdAndUserId(int productId, int userId) {
        CartItem item = null;
        String sql = "SELECT * FROM cart_item WHERE product_id = ? AND user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, productId, userId);
        while(results.next()) {
            item = mapRowToCartItem(results);
        }
        return item;
    }

    @Override
    public List<CartItem> getByUser(int userId) {
        List<CartItem> cartItemList = new ArrayList<>();
        String sql = "SELECT * FROM cart_item WHERE user_id = ? ORDER BY cart_item_id";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()) {
            CartItem item = mapRowToCartItem(results);
            cartItemList.add(item);
        }
        return cartItemList;
    }

    @Override
    public int addItem(CartItem item) {
        String sql = "INSERT INTO cart_item(user_id, product_id, quantity) VALUES (?, ?, ?) RETURNING cart_item_id";
        int addedId = jdbcTemplate.queryForObject(sql, int.class, item.getUserId(), item.getProductId(), item.getQuantity());
        return addedId;

    }

    @Override
    public void update(CartItem item) {
        String sql = "UPDATE cart_item SET quantity = ? WHERE cart_item_id = ?";
        jdbcTemplate.update(sql, item.getQuantity(), item.getCartId());

    }

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

    //THIS MAY NOT BE NEEDED AT THIS POINT SINCE getByUserId works properly
    //mapRowToCartItem won't work in this scenario
    @Override
    public List<CartItem> getProductsById(int productId) {
        List<CartItem> cartByProdId = new ArrayList<>();
        String sql = "SELECT product.name, product.description, product.price FROM product " +
                        "JOIN cart_item ON cart_item.product_id = product.product_id " +
                        "JOIN users ON users.user_id = cart_item.user_id " +
                        "WHERE cart_item.product_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, productId);
        while(results.next()) {
            CartItem cartItem = mapRowToCartItem(results);
            cartByProdId.add(cartItem);
        }
        return cartByProdId;
    }

    //CHANGED TO RETURN LIST of mapped objects instead of cartItem object
    //THIS WORKS SO FAR
    @Override
    public List<Map<String, Object>> getByUserId(int userId) {
//        List<CartItem> productsForUser = new ArrayList<>();
        List<Map<String, Object>> products = new ArrayList<>();
        String sql = "SELECT product.name, product.description, product.price FROM product " +
                "JOIN cart_item ON cart_item.product_id = product.product_id " +
                "JOIN users ON users.user_id = cart_item.user_id " +
                "WHERE cart_item.user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()) {
            Map<String, Object> product = new HashMap<>();
            product.put("name", results.getString("name"));
            product.put("description", results.getString("description"));
            product.put("price", results.getBigDecimal("price"));
            products.add(product);
        }
        return products;
    }

    //THIS ALSO WORKS, may not be needed
    @Override
    public List<Product> getProductsByUserId(int userId) {
        List<Product> usersProducts = new ArrayList<>();
        String sql = "SELECT * FROM product " +
                    "JOIN cart_item ON cart_item.product_id = product.product_id " +
                    "JOIN users ON users.user_id = cart_item.user_id " +
                    "WHERE cart_item.user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()) {

        }
        return usersProducts;
    }

    @Override
    public void deleteCartItem(int cartId, int userId) {
        String sql = "DELETE FROM cart_item WHERE cart_item_id = ? AND user_id = ?";
        jdbcTemplate.update(sql, cartId, userId);
    }


    @Override
    public void clearCartByUser(int userId) {

    }


    private CartItem mapRowToCartItem(SqlRowSet results) {
        CartItem cartItem = new CartItem();
        cartItem.setCartId(results.getInt("cart_item_id"));
        cartItem.setUserId(results.getInt("user_id"));
        cartItem.setProductId(results.getInt("product_id"));
        cartItem.setQuantity(results.getInt("quantity"));
        return cartItem;
    }



}
