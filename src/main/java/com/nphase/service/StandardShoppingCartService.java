package com.nphase.service;

import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;

public class StandardShoppingCartService implements ShoppingCartService {

    @Override
    public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
        return shoppingCart.getProducts()
                .stream()
                .map(product -> product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
