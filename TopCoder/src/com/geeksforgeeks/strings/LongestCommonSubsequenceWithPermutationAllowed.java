package com.geeksforgeeks.strings;

import javafx.collections.transformation.SortedList;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by sshil on 10/25/16.
 */
//http://www.geeksforgeeks.org/longest-common-subsequence-with-permutations-allowed/
public class LongestCommonSubsequenceWithPermutationAllowed {

    public void find(String str1, String str2) {
        List<String> result = new ArrayList<>();
        Map<String, Long> charCountMap = str1.chars()
                .mapToObj(i -> (char)i+"").collect(Collectors.groupingBy(Function.identity(),
                Collectors.counting()));
        str2.chars().mapToObj(i->(char)i+"").forEach(s -> calculate(s, charCountMap, result));
        Collections.sort(result);
        result.stream().forEach(System.out::print);
    }

    private static void calculate(String input, Map<String, Long> map, List<String> result){
        if (map.containsKey(input) && map.get(input) >= 1){
            Long count = map.get(input);
            map.put(input, count-1);
            result.add(input);
        }
    }

    public static void main(String[] args) {
        new LongestCommonSubsequenceWithPermutationAllowed().find("aaaa","baba");
    }
}
