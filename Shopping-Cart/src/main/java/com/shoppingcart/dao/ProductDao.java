package com.shoppingcart.dao;

import com.shoppingcart.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> listProducts();

    Product productById(int id);


    List<Product> getProductsByUserId(int userId);

    List<Product> getProductsInWishList(int wishlistId);

    List<Product> filterSkuOrName(String productSku, String name);

    List<Product> findProductsByName(String productName);

    List<Product> findProductsBySku(String product_sku);



}
