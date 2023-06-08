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
    public Product productInfo(int id) {
        return null;
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
