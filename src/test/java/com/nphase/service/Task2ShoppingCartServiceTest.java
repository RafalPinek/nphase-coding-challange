package com.nphase.service;


import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;
import com.nphase.factory.ShoppingCartServiceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Stream;

public class Task2ShoppingCartServiceTest {

    private final ShoppingCartService service = ShoppingCartServiceFactory.task2Service();

    @ParameterizedTest
    @MethodSource("provideProductsLists")
    public void calculatesPrices(List<Product> products, BigDecimal expectedPrice) {
        ShoppingCart cart = new ShoppingCart(products);

        BigDecimal result = service.calculateTotalPrice(cart);

        Assertions.assertEquals(expectedPrice, result);
    }

    private static Stream<Arguments> provideProductsLists() {
        return Stream.of(
                Arguments.of(List.of(
                        new Product("Tea", BigDecimal.valueOf(5.0), 5),
                        new Product("Coffee", BigDecimal.valueOf(3.5), 3)),
                        new BigDecimal("33.00").setScale(2, RoundingMode.HALF_UP)),
                Arguments.of(List.of(
                        new Product("Tea", BigDecimal.valueOf(5.0), 2),
                        new Product("Coffee", BigDecimal.valueOf(6.5), 1),
                        new Product("Fries", BigDecimal.valueOf(10.0), 3)),
                        new BigDecimal("46.50").setScale(2, RoundingMode.HALF_UP)),
                Arguments.of(List.of(
                        new Product("Tea", BigDecimal.valueOf(5.0), 5),
                        new Product("Coffee", BigDecimal.valueOf(3.5), 3),
                        new Product("Fries", BigDecimal.valueOf(10), 7)),
                        new BigDecimal("96.00").setScale(2, RoundingMode.HALF_UP)),
                Arguments.of(List.of(
                        new Product("Tea", BigDecimal.valueOf(5.0), 0),
                        new Product("Coffee", BigDecimal.valueOf(6.5), 0),
                        new Product("Fries", BigDecimal.valueOf(6.5), 0)),
                        new BigDecimal("0.00").setScale(2, RoundingMode.HALF_UP))
        );
    }

}