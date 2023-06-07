package com.shoppingcart.dao;

import com.shoppingcart.model.Product;

import java.util.List;

public class JdbcProductDao implements ProductDao {

    @Override
    public List<Product> listProducts() {
        return null;
    }

    @Override
    public List<Product> findProductsByName(String productName) {
        return null;
    }

    @Override
    public List<Product> findProductsBySku(String product_sku) {
        return null;
    }

    @Override
    public Product productInfo(int id) {
        return null;
    }
}
