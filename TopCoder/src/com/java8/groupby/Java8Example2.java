package com.java8.groupby;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by SumanChandra on 9/4/2016.
 */
public class Java8Example2 {

    public static void main(String[] args) {
        List<String> items =
                Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");

        Map<String, Long> result =
                items.stream().collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );

        Map<String, Long> finalMap = new HashMap<>();
        result.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(e -> finalMap.put(e.getKey(), e.getValue()));
        System.out.println(finalMap);
    }
}
