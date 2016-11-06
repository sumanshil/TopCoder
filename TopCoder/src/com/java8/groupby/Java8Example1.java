package com.java8.groupby;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by SumanChandra on 9/4/2016.
 */
public class Java8Example1 {


    public static void main(String[] args) {
        List<String> items = Arrays.asList("apple",
                "apple",
                "banana",
                "apple",
                "orange",
                "banana",
                "papaya");
        Map<String, Long> result =
                items.stream().collect(
                        Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));
        System.out.println(result);
    }
}
