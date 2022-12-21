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

public class Task4ShoppingCartServiceTest {

    private final ShoppingCartService serviceDiscount_10_Threshold_3 = ShoppingCartServiceFactory.task4Service(BigDecimal.TEN, 3);
    private final ShoppingCartService serviceDiscount_50_Threshold_10 = ShoppingCartServiceFactory.task4Service(BigDecimal.valueOf(50.0), 10);
    private final ShoppingCartService serviceDiscount_5_Threshold_2 = ShoppingCartServiceFactory.task4Service(BigDecimal.valueOf(5.0), 2);

    @ParameterizedTest
    @MethodSource("provideProductsListsDiscount_10_Threshold_3")
    public void calculatesPricesDiscount_10_Threshold_3(List<Product> products, BigDecimal expectedPrice) {
        ShoppingCart cart = new ShoppingCart(products);

        BigDecimal result = serviceDiscount_10_Threshold_3.calculateTotalPrice(cart);

        Assertions.assertEquals(expectedPrice, result);
    }

    private static Stream<Arguments> provideProductsListsDiscount_10_Threshold_3() {
        return Stream.of(
                Arguments.of(List.of(
                        new Product("Tea", BigDecimal.valueOf(5.3), 2, "drinks"),
                        new Product("Coffee", BigDecimal.valueOf(3.5), 2, "drinks"),
                        new Product("Fries", BigDecimal.valueOf(8), 2, "food")),
                        BigDecimal.valueOf(31.84)),
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

    @ParameterizedTest
    @MethodSource("provideProductsListsDiscount_50_Threshold_10")
    public void calculatesPricesDiscount_50_Threshold_10(List<Product> products, BigDecimal expectedPrice) {
        ShoppingCart cart = new ShoppingCart(products);

        BigDecimal result = serviceDiscount_50_Threshold_10.calculateTotalPrice(cart);

        Assertions.assertEquals(expectedPrice, result);
    }

    private static Stream<Arguments> provideProductsListsDiscount_50_Threshold_10() {
        return Stream.of(
                Arguments.of(List.of(
                        new Product("Tea", BigDecimal.valueOf(5.3), 6, "drinks"),
                        new Product("Coffee", BigDecimal.valueOf(3.5), 6, "drinks"),
                        new Product("Fries", BigDecimal.valueOf(8), 2, "food")),
                        new BigDecimal("42.40").setScale(2, RoundingMode.HALF_UP)),
                Arguments.of(List.of(
                        new Product("Tea", BigDecimal.valueOf(5.3), 2, "drinks"),
                        new Product("Coffee", BigDecimal.valueOf(3.5), 1, "drinks"),
                        new Product("Fries", BigDecimal.valueOf(8), 12, "food")),
                        new BigDecimal("62.10").setScale(2, RoundingMode.HALF_UP)),
                Arguments.of(List.of(
                        new Product("Tea", BigDecimal.valueOf(5.3), 10, "drinks"),
                        new Product("Coffee", BigDecimal.valueOf(3.5), 10, "drinks"),
                        new Product("Fries", BigDecimal.valueOf(8), 4, "food")),
                        new BigDecimal("76.00").setScale(2, RoundingMode.HALF_UP)),
                Arguments.of(List.of(
                        new Product("Tea", BigDecimal.valueOf(5.3), 2, "drinks"),
                        new Product("Coffee", BigDecimal.valueOf(3.5), 6, "drinks"),
                        new Product("Gold", BigDecimal.valueOf(30.0), 6, "commodities"),
                        new Product("Silver", BigDecimal.valueOf(20.0), 2, "commodities"),
                        new Product("Wood", BigDecimal.valueOf(40.0), 1, "commodities"),
                        new Product("Tomatoes", BigDecimal.valueOf(3.5), 2, "food"),
                        new Product("Fries", BigDecimal.valueOf(8), 2, "food")),
                        new BigDecimal("314.60").setScale(2, RoundingMode.HALF_UP)),
                Arguments.of(List.of(
                        new Product("Tea", BigDecimal.valueOf(5.3), 5, "drinks"),
                        new Product("Coffee", BigDecimal.valueOf(3.5), 5, "drinks"),
                        new Product("Fries", BigDecimal.valueOf(8), 0, "food")),
                        new BigDecimal("44.00").setScale(2, RoundingMode.HALF_UP))
        );
    }

    @ParameterizedTest
    @MethodSource("provideProductsListsDiscount_5_Threshold_2")
    public void calculatesPricesDiscount_5_Threshold_2(List<Product> products, BigDecimal expectedPrice) {
        ShoppingCart cart = new ShoppingCart(products);

        BigDecimal result = serviceDiscount_5_Threshold_2.calculateTotalPrice(cart);

        Assertions.assertEquals(expectedPrice, result);
    }

    private static Stream<Arguments> provideProductsListsDiscount_5_Threshold_2() {
        return Stream.of(
                Arguments.of(List.of(
                        new Product("Tea", BigDecimal.valueOf(5.3), 1, "drinks"),
                        new Product("Coffee", BigDecimal.valueOf(3.5), 1, "drinks"),
                        new Product("Fries", BigDecimal.valueOf(8), 2, "food")),
                        new BigDecimal("24.80").setScale(2, RoundingMode.HALF_UP)),
                Arguments.of(List.of(
                        new Product("Tea", BigDecimal.valueOf(5.3), 1, "drinks"),
                        new Product("Coffee", BigDecimal.valueOf(3.5), 1, "drinks"),
                        new Product("Fries", BigDecimal.valueOf(8), 1, "food")),
                        new BigDecimal("16.80").setScale(2, RoundingMode.HALF_UP)),
                Arguments.of(List.of(
                        new Product("Tea", BigDecimal.valueOf(5.3), 1, "drinks"),
                        new Product("Coffee", BigDecimal.valueOf(3.5), 0, "drinks"),
                        new Product("Fries", BigDecimal.valueOf(8), 1, "food")),
                        new BigDecimal("13.30").setScale(2, RoundingMode.HALF_UP)),
                Arguments.of(List.of(
                        new Product("Tea", BigDecimal.valueOf(5.3), 2, "drinks"),
                        new Product("Coffee", BigDecimal.valueOf(3.5), 1, "drinks"),
                        new Product("Gold", BigDecimal.valueOf(30.0), 1, "commodities"),
                        new Product("Silver", BigDecimal.valueOf(20.0), 1, "commodities"),
                        new Product("Wood", BigDecimal.valueOf(40.0), 1, "commodities"),
                        new Product("Tomatoes", BigDecimal.valueOf(3.5), 3, "food"),
                        new Product("Fries", BigDecimal.valueOf(8), 2, "food")),
                        BigDecimal.valueOf(124.07)),
                Arguments.of(List.of(
                        new Product("Tea", BigDecimal.valueOf(5.3), 0, "drinks"),
                        new Product("Coffee", BigDecimal.valueOf(3.5), 0, "drinks"),
                        new Product("Fries", BigDecimal.valueOf(8), 0, "food")),
                        new BigDecimal("0.00").setScale(2, RoundingMode.HALF_UP))
        );
    }

}