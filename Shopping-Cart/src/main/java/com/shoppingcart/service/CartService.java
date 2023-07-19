package com.shoppingcart.service;

import com.shoppingcart.dao.CartItemDao;
import com.shoppingcart.dao.ProductDao;
import com.shoppingcart.dao.UserDao;
import com.shoppingcart.model.Cart;
import com.shoppingcart.model.CartItem;
import com.shoppingcart.model.Product;
import com.shoppingcart.model.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CartService {

    private CartItemDao cartItemDao;
    private ProductDao productDao;
    private UserDao userDao;

    private TaxService taxService;


    public CartService(CartItemDao cartItemDao, ProductDao productDao, UserDao userDao, TaxService taxService) {
        this.cartItemDao = cartItemDao;
        this.productDao = productDao;
        this.userDao = userDao;
        this.taxService = taxService;
    }


    //Get current user
    public User getUser(Principal principal) {
        String user = principal.getName();
        return userDao.findByUsername(user);
    }


    public Cart getCart(Principal principal) {
        User user = getUser(principal);
        int userId = user.getUserId();

        List<CartItem> usersCart = cartItemDao.getByUser(userId);
        Map<Integer, Product> userProducts = getUserProducts(userId);

        Cart cart = new Cart();
        cart.setCartItems(usersCart);

        for (CartItem item : usersCart) {
            item.setProduct(userProducts.get(item.getProductId()));
        }

        BigDecimal taxRate = taxService.getTaxRate(user.getStateCode());
        BigDecimal subtotal = cart.getSubTotal();
        BigDecimal tax = subtotal.multiply(taxRate).setScale(2, RoundingMode.UP);

        return cart;
    }

    private Map<Integer, Product> getUserProducts(int userId) {
        List<Product> userProducts = productDao.getProductsByUserId(userId);
        Map<Integer, Product> productMap = new HashMap<>();

        for (Product product : userProducts) {
            productMap.put(product.getId(), product);
        }

        return productMap;
    }

    public CartItem addToCart(Principal principal, CartItem item) {
        User user = getUser(principal);
        int userId = user.getUserId();

        CartItem itemAlreadyInCart = cartItemDao.getByProdAndUserId(item.getProductId(), userId);

        if (itemAlreadyInCart == null) {
            int newId = cartItemDao.addItem(item);
            return cartItemDao.getById(newId, userId);
        } else {
            itemAlreadyInCart.setQuantity(itemAlreadyInCart.getQuantity() + item.getQuantity());
            cartItemDao.update(itemAlreadyInCart);
            return cartItemDao.getById(itemAlreadyInCart.getCartId(), userId);
        }
    }


    public void removeItemFromCart(Principal principal, int itemId) {
        User user = getUser(principal);
        int userId = user.getUserId();

        cartItemDao.deleteCartItem(itemId, userId);
    }






}
