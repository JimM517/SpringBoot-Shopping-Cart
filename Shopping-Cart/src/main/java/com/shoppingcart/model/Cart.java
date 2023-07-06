package com.shoppingcart.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {

    private BigDecimal subTotal;
    private BigDecimal taxAmount;
    private BigDecimal cartTotal;
    List<CartItem> cartItems = new ArrayList<>();

    public Cart() {

    }

    public Cart(BigDecimal subTotal, BigDecimal taxAmount, BigDecimal cartTotal, List<CartItem> cartItems) {
        this.subTotal = subTotal;
        this.taxAmount = taxAmount;
        this.cartTotal = cartTotal;
        this.cartItems = cartItems;
    }

    //GETTERS AND SETTERS
    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(BigDecimal cartTotal) {
        this.cartTotal = cartTotal;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "subTotal=" + subTotal +
                ", taxAmount=" + taxAmount +
                ", cartTotal=" + cartTotal +
                ", cartItems=" + cartItems +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(subTotal, cart.subTotal) && Objects.equals(taxAmount, cart.taxAmount) && Objects.equals(cartTotal, cart.cartTotal) && Objects.equals(cartItems, cart.cartItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subTotal, taxAmount, cartTotal, cartItems);
    }
}
