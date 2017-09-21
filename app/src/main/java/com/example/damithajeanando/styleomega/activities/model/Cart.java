package com.example.damithajeanando.styleomega.activities.model;

/**
 * Created by Damitha Jeanando on 9/20/2017.
 */

public class Cart {

    int cartId;
    String cartName;
    String cartPrice;
    String cartQty;
    String cartImg;

    public Cart() {
        this.cartId = cartId;
        this.cartName = cartName;
        this.cartPrice = cartPrice;
        this.cartQty = cartQty;
        this.cartImg = cartImg;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getCartName() {
        return cartName;
    }

    public void setCartName(String cartName) {
        this.cartName = cartName;
    }

    public String getCartPrice() {
        return cartPrice;
    }

    public void setCartPrice(String cartPrice) {
        this.cartPrice = cartPrice;
    }

    public String getCartQty() {
        return cartQty;
    }

    public void setCartQty(String cartQty) {
        this.cartQty = cartQty;
    }

    public String getCartImg() {
        return cartImg;
    }

    public void setCartImg(String cartImg) {
        this.cartImg = cartImg;
    }
}
