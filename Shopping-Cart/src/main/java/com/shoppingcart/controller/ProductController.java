package com.shoppingcart.controller;

import com.shoppingcart.dao.ProductDao;
import com.shoppingcart.model.Product;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/products")
public class ProductController {

    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

//    @GetMapping
//    public List<Product> list() {
//        return productDao.listProducts();
//    }


    // TODO: 6/7/2023  WORK ON FILTERING RESULTS WITH TWO PARAMS //


    @GetMapping
    public List<Product> findProducts(@RequestParam(value = "product_sku", defaultValue = "") String product_sku, @RequestParam(value = "name", defaultValue = "") String name) {
        if (product_sku != null) {
            return productDao.findProductsBySku(product_sku);
        }
        if (name != null) {
            return productDao.findProductsByName(name);
        }
        return productDao.listProducts();
    }




}
