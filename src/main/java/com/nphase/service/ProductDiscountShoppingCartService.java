package com.nphase.service;

import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.nphase.service.Consts.*;

public class ProductDiscountShoppingCartService implements ShoppingCartService {

    @Override
    public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
        return shoppingCart.getProducts()
                .stream()
                .map(this::calculateProductTotalPrice)
                .reduce(BigDecimal::add)
                .map(bd -> bd.setScale(2, RoundingMode.HALF_UP))
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal calculateProductTotalPrice(Product product) {
        BigDecimal price = product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity()));
        if (discountApplies(product)) {
            price = applyDiscount(price);
        }
        return price;
    }

    private boolean discountApplies(Product product) {
        return product.getQuantity() > AMOUNT_THRESHOLD;
    }

    private BigDecimal applyDiscount(BigDecimal price) {
        return price.multiply(ONE_HUNDRED.subtract(DISCOUNT)).divide(ONE_HUNDRED);
    }
}
