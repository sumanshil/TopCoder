package com.java8.lambda;

import java.util.stream.Stream;

public class LambdaTest {

    public static void reduceTest() {
        System.out.println(
                Stream.of(1, 2, 3, 4, 5).reduce((a, b) -> {
                    System.out.println("In accumulator: " + a + ", " + b);
                    return a + b;
                }).get());
    }

    public static void reduceTest1() {
        System.out.println(
                Stream.of(1, 2, 3, 4, 5).reduce(0, (a, b) -> {
                    System.out.println("In accumulator: " + a + ", " + b);
                    return a + b;
                }));
    }


    //U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U>
    // combiner): An identity/initial value of any type can be supplied to it,
    // an accumulator is executed on the stream elements. Accumulator accepts two
    // arguments - one of the same type as the identity value and other of stream
    // element type and returns an element (reduced value) of the same type as the
    // identity value. The first argument is the result of the accumulator's execution
    // from the previous iteration and the second argument is the current stream element.
    // A combiner has to be specified which is executed when a stream is executed
    // in parallel, it combines the reduced values from parallel executions.
    // The reduce operation returns the reduced value of the same type as the identity
    // value.
    public static void reduceTest2() {
        System.out.println(
                Stream.of(1, 2, 3, 4, 5).reduce(0, (a, b) -> {
                    System.out.println("In accumulator: " + a + ", " + b);
                    return a + b;
                }, (a, b) -> {
                    System.out.println("In combiner: " + a + ", " + b);
                    return a + b;
                }));
    }


    public static void main(String[] args) {
        reduceTest1();
    }
}
