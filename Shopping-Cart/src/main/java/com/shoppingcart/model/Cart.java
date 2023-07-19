package com.shoppingcart.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private BigDecimal subTotal;
    private BigDecimal taxAmount;
    private BigDecimal cartTotal;
    List<CartItem> cartItems = new ArrayList<>();



    public Cart() {
        this.cartItems = new ArrayList<>();
        this.taxAmount = new BigDecimal("0.00");
    }



    public Cart(List<CartItem> cartItems) {
        this();
        this.cartItems = cartItems;
    }

    //GETTERS AND SETTERS
    public BigDecimal getSubTotal() {
       if (subTotal == null) {
           calculateSubTotal();
       }
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
        if (cartTotal == null) {
            calculateTotal();
        }
        return cartTotal;
    }

    public void setCartTotal(BigDecimal cartTotal) {
        this.cartTotal = cartTotal;
    }

    public CartItem[] getCartItems() {
        CartItem result[] = new CartItem[cartItems.size()];
        return cartItems.toArray(result);
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }


    public void calculateSubTotal() {
        BigDecimal subtotal = BigDecimal.ZERO;

        for(CartItem item : cartItems) {
            subtotal = subtotal.add(item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())));
        }
        subTotal = subtotal;
    }

    public void calculateTotal() {
        BigDecimal total = getSubTotal().add(taxAmount);
        cartTotal = total;
    }


}
