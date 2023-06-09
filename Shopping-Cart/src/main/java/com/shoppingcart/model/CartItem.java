package com.shoppingcart.model;

import java.util.Objects;

public class CartItem {

    private int cartId;
    private int userId;
    private int productId;
    private int quantity;

    private Product product;

    public CartItem() {};



    public CartItem(int cartId, int userId, int productId, int quantity, Product product) {
        this.cartId = cartId;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.product = product;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "CartItem{" +
                "cartId=" + cartId +
                ", userId=" + userId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return cartId == cartItem.cartId && userId == cartItem.userId && productId == cartItem.productId && quantity == cartItem.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, userId, productId, quantity);
    }
}
