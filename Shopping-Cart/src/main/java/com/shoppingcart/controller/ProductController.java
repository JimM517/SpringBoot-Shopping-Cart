package com.shoppingcart.controller;

import com.shoppingcart.dao.ProductDao;
import com.shoppingcart.model.Product;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


//    @GetMapping
//    public List<Product> findProducts(@RequestParam(required = false) String sku, @RequestParam(required = false) String name) {
//        if (product_sku != null) {
//            return productDao.findProductsBySku(product_sku);
//        }
//        if (name != null) {
//            return productDao.findProductsByName(name);
//        }
//        return productDao.listProducts();
//    }


    // THIS IS WORKING
    @GetMapping(path = "/{id}")
    public Product findProductById(@PathVariable int id) {
        return productDao.productById(id);
    }




}
