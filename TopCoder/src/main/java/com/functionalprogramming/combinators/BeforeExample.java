package com.functionalprogramming.combinators;

import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.function.Function;

//https://dzone.com/articles/introducing-combinators-part-1?fromrel=true
public class BeforeExample {
    void demo() {
        System.out.println("----------------------------------");
        System.out.println("Starting BEFORE combinator demo...");
        System.out.println("----------------------------------");
        Function<BigDecimal, String> addTax = this::addTax;
        Consumer<BigDecimal> before = this::before;
        Function<BigDecimal, String> addTaxDecorated =
                Before.decorate(before, addTax);
        BigDecimal argument = new BigDecimal("100");
        String result = addTaxDecorated.apply(argument);
        System.out.println("Done - Result is " + result);
        System.out.println();
    }
    private void before(BigDecimal argument) {
        System.out.println("BEFORE: Argument is " + argument);
    }
    private String addTax(BigDecimal amount) {
        System.out.println("Adding heavy taxes to poor citizen...");
        return "$" + amount.multiply(new BigDecimal("1.22"));
    }

    public static void main(String[] args) {
        new BeforeExample().demo();
    }
}
