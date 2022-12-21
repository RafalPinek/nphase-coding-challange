package com.nphase.factory;

import com.nphase.service.*;

import java.math.BigDecimal;

public class ShoppingCartServiceFactory {

    private ShoppingCartServiceFactory() {}

    public static ShoppingCartService task1Service() {
        return new StandardShoppingCartService();
    }

    public static ShoppingCartService task2Service() {
        return new ProductDiscountShoppingCartService();
    }

    public static ShoppingCartService task3Service() {
        return new CategoryDiscountShoppingCartService();
    }

    public static ShoppingCartService task4Service(BigDecimal discount, int threshold) {
        return new ConfigurableCategoryDiscountShoppingCartService(discount, threshold);
    }
}
