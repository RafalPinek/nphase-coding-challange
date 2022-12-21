package com.nphase.service;


import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;
import com.nphase.factory.ShoppingCartServiceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

public class Task1ShoppingCartServiceTest {
    private final ShoppingCartService service = ShoppingCartServiceFactory.task1Service();

    @Test
    public void calculatesPrice() {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.0), 2),
                new Product("Coffee", BigDecimal.valueOf(6.5), 1)
        ));

        BigDecimal result = service.calculateTotalPrice(cart);

        Assertions.assertEquals(result, BigDecimal.valueOf(16.5));
    }

}