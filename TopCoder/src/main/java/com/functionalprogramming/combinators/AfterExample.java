package com.functionalprogramming.combinators;

import java.math.BigDecimal;
import java.util.function.Function;

public class AfterExample {

    void demo() {
        System.out.println("---------------------------------");
        System.out.println("Starting AFTER combinator demo...");
        System.out.println("---------------------------------");
        Function<BigDecimal, String> addTaxDecorated =
                After.decorate(this::addTax, this::after);
        String result = addTaxDecorated.apply(new BigDecimal("1000"));
        System.out.println("Done - Result is " + result);
        System.out.println();
    }
    private String addTax(BigDecimal amount) {
        System.out.println("Adding heavy taxes to poor citizen...");
        return "$" + amount.multiply(new BigDecimal("1.22"));
    }
    private void after(BigDecimal argument, String result) {
        System.out.println("AFTER: Argument is " + argument + ", Result is " + result);
    }

    public static void main(String[] args) {
        new AfterExample().demo();
    }
}
