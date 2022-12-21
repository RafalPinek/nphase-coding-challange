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

public class Task3ShoppingCartServiceTest {

    private final ShoppingCartService service = ShoppingCartServiceFactory.task3Service();

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
                        new Product("Tea", BigDecimal.valueOf(5.3), 2, "drinks"),
                        new Product("Coffee", BigDecimal.valueOf(3.5), 2, "drinks"),
                        new Product("Fries", BigDecimal.valueOf(8), 2, "food")),
                        new BigDecimal("31.84").setScale(2, RoundingMode.HALF_UP)),
                Arguments.of(List.of(
                        new Product("Tea", BigDecimal.valueOf(5.3), 2, "drinks"),
                        new Product("Coffee", BigDecimal.valueOf(3.5), 1, "drinks"),
                        new Product("Fries", BigDecimal.valueOf(8), 2, "food")),
                        new BigDecimal("30.10").setScale(2, RoundingMode.HALF_UP)),
                Arguments.of(List.of(
                        new Product("Tea", BigDecimal.valueOf(5.3), 2, "drinks"),
                        new Product("Coffee", BigDecimal.valueOf(3.5), 2, "drinks"),
                        new Product("Fries", BigDecimal.valueOf(8), 4, "food")),
                        BigDecimal.valueOf(44.64)),
                Arguments.of(List.of(
                        new Product("Tea", BigDecimal.valueOf(5.3), 2, "drinks"),
                        new Product("Coffee", BigDecimal.valueOf(3.5), 6, "drinks"),
                        new Product("Gold", BigDecimal.valueOf(30.0), 1, "commodities"),
                        new Product("Silver", BigDecimal.valueOf(20.0), 2, "commodities"),
                        new Product("Wood", BigDecimal.valueOf(40.0), 1, "commodities"),
                        new Product("Tomatoes", BigDecimal.valueOf(3.5), 2, "food"),
                        new Product("Fries", BigDecimal.valueOf(8), 2, "food")),
                        BigDecimal.valueOf(148.14)),
                Arguments.of(List.of(
                        new Product("Tea", BigDecimal.valueOf(5.3), 2, "drinks"),
                        new Product("Coffee", BigDecimal.valueOf(3.5), 2, "drinks"),
                        new Product("Fries", BigDecimal.valueOf(8), 0, "food")),
                        BigDecimal.valueOf(15.84))
        );
    }

}