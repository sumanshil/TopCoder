package com.leetcode.array.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UncommonWordsFromTwoSentences {
    public String[] uncommonFromSentences(String A, String B) {

        Map<String, Long> map = Arrays.asList(A.split(" ")).stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Consumer<String> consumer = value -> {
            if (!map.containsKey(value)) {
                map.put(value, 1L);
            } else {
                map.put(value, map.get(value) + 1);
            }
        };

        Arrays.asList(B.split(" ")).stream().forEach(consumer);

        Set<String> set = map.entrySet().stream()
                .filter( stringLongEntry -> stringLongEntry.getValue() == 1L).map(Map.Entry::getKey).
                        collect(Collectors.toSet());
        return (String[])set.toArray(new String[0]);
    }


    public static void main(String[] args) {
        String A = "apple apple";
        String B = "banana";
        String[] res = new UncommonWordsFromTwoSentences().uncommonFromSentences(A, B);
        Arrays.stream(res).forEach(System.out::print);
    }
}

