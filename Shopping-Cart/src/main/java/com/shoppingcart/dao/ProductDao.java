package com.shoppingcart.dao;

import com.shoppingcart.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> listProducts();

    List<Product> findProductsByName(String productName);

    List<Product> findProductsBySku(String product_sku);

    Product productInfo(int id);

}
