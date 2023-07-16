package com.shoppingcart.dao;

import com.shoppingcart.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> listProducts() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM product";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Product product = mapRowToProduct(results);
            productList.add(product);
        }
        return productList;
    }

    @Override
    public List<Product> findProductsByName(String productName) {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT product_id, product_sku, name, description, price, image_name FROM product WHERE name LIKE ?";
        String filter = '%' + productName + '%';
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, filter);
        while(results.next()) {
            Product product = mapRowToProduct(results);
            productList.add(product);
        }
        return productList;
    }

    @Override
    public List<Product> findProductsBySku(String product_sku) {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT product_id, product_sku, name, description, price, image_name FROM product WHERE product_sku LIKE ?";
        String filter = '%' + product_sku + '%';
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, filter);
        while(results.next()) {
            Product product = mapRowToProduct(results);
            productList.add(product);
        }
        return productList;
    }

    @Override
    public Product productById(int id) {
        Product product = null;
        String sql = "SELECT product_id, product_sku, name, description, price, image_name FROM product WHERE product_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            product = mapRowToProduct(results);
        }
        return product;
    }

    @Override
    public List<Product> getProductsByUserId(int userId) {
        List<Product> userList = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE product_id IN (SELECT product_id FROM cart_item WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()) {
            Product product = mapRowToProduct(results);
            userList.add(product);
        }
        return userList;
    }

    @Override
    public List<Product> getProductsInWishList(int wishlistId) {
        List<Product> wishList = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE product_id IN (SELECT product_id FROM wishlist_item WHERE wishlist_id = ?)";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, wishlistId);
        while(results.next()) {
            Product product = mapRowToProduct(results);
            wishList.add(product);
        }
        return wishList;
    }

    @Override
    public List<Product> filterSkuOrName(String productSku, String name) {
        List<Product> productList = new ArrayList<>();
        String nameLike = "%" + (name == null ? "" : name) + "%";

        // checking if sku is empty or null, won't include in where clause
        boolean isSku = productSku != null && productSku.trim().length() > 0;

        String sql = "SELECT * FROM product WHERE name ILIKE ? " +
                (isSku ? "AND product_sku = ? " : "") +
                "ORDER BY product_id";
        SqlRowSet results;
        if (isSku) {
            results = jdbcTemplate.queryForRowSet(sql, nameLike, productSku);
        } else {
            results = jdbcTemplate.queryForRowSet(sql, nameLike);
        }
        while(results.next()) {
            Product product = mapRowToProduct(results);
            productList.add(product);
        }
        return productList;
    }


    private Product mapRowToProduct(SqlRowSet results) {
        Product product = new Product();
        product.setId(results.getInt("product_id"));
        product.setProduct_sku(results.getString("product_sku"));
        product.setName(results.getString("name"));
        product.setDescription(results.getString("description"));
        product.setPrice(results.getBigDecimal("price"));
        product.setImageName(results.getString("image_name"));
        return product;
    }

}
