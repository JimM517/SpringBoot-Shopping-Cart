package com.shoppingcart.controller;

import com.shoppingcart.dao.ProductDao;
import com.shoppingcart.model.Product;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/products")
public class ProductController {

    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

 @GetMapping()
    public List<Product> getProds(@RequestParam(required = false) String sku, @RequestParam(required = false) String name) {
        if (sku == null && name == null) {
            return productDao.listProducts();
        } else {
            return productDao.filterSkuOrName(sku, name);
        }
 }

 @GetMapping(path = "/{id}")
    public Product getProductById(@PathVariable int id) {
        return productDao.productById(id);
 }



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





//    // THIS IS WORKING
//    @GetMapping(path = "/{id}")
//    public Product findProductById(@PathVariable int id) {
//        return productDao.productById(id);
//    }




}
