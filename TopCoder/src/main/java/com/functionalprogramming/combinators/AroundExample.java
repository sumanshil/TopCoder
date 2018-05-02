package com.functionalprogramming.combinators;

import java.math.BigDecimal;
import java.util.function.Function;

public class AroundExample {

    void demo() {
        System.out.println("----------------------------------");
        System.out.println("Starting AROUND combinator demo...");
        System.out.println("----------------------------------");
        Function<BigDecimal, String> addTaxDecorated =
                Around.decorate(this::addTax, this::around);
        String result = addTaxDecorated.apply(new BigDecimal("10000"));
        System.out.println("Done - Result is " + result);
        System.out.println();
    }
    private String addTax(BigDecimal amount) {
        System.out.println("Adding heavy taxes to poor citizen...");
        return "$" + amount.multiply(new BigDecimal("1.22"));
    }
    private void around(Around.Executable<String> function, BigDecimal argument) {
        System.out.println("BEFORE: Amount is " + argument);
        String result = function.execute(); // function executed here!
        System.out.println("AFTER: Result is " + result);
    }

    public static void main(String[] args) {
        new AroundExample().demo();
    }
}
