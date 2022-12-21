package com.nphase.service;

import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import static com.nphase.service.Consts.ONE_HUNDRED;

public class ConfigurableCategoryDiscountShoppingCartService implements ShoppingCartService {

    private final BigDecimal discount;

    private final int threshold;

    public ConfigurableCategoryDiscountShoppingCartService(BigDecimal discount, int threshold) {
        this.discount = discount;
        this.threshold = threshold;
    }

    @Override
    public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
        return shoppingCart.getProducts()
                .stream()
                .collect(Collectors.groupingBy(Product::getCategory))
                .values()
                .stream()
                .map(this::calculateCategoryTotalPrice)
                .reduce(BigDecimal::add)
                .map(bd -> bd.setScale(2, RoundingMode.HALF_UP))
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal calculateCategoryTotalPrice(List<Product> products) {
        BigDecimal price = products.stream()
                .map(product -> product.getPricePerUnit().multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        if (discountApplies(products)) {
            price = applyDiscount(price);
        }
        return price;
    }

    private boolean discountApplies(List<Product> products) {
        int categoryProductsAmount = products.stream()
                .map(Product::getQuantity)
                .reduce(Integer::sum)
                .orElse(0);
        return categoryProductsAmount > threshold;
    }

    private BigDecimal applyDiscount(BigDecimal price) {
        return price.multiply(ONE_HUNDRED.subtract(discount)).divide(ONE_HUNDRED);
    }
}
