package com.example.cartview;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static Cart instance;
    private final List<CartItem> cartItems;

    private Cart() {
        cartItems = new ArrayList<>();
    }

    public static synchronized Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void addItem(CartItem item) {
        cartItems.add(item);
    }

    public List<CartItem> getItems() {
        return cartItems;
    }

    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice();
        }
        return total;
    }

    public void clearCart() {
        cartItems.clear();
    }
}

