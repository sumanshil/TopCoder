package com.codewars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//https://leetcode.com/problems/generate-parentheses/discuss/10127/An-iterative-method.
public class GenerateParenthesis {


    public List<String> generateParenthesisRecursive(int n) {
        StringBuilder stringBuilder =  new StringBuilder("");
        List<String> list = new ArrayList<>();
        recursive(stringBuilder, n, n, list);
        return list;
    }

    private void recursive(StringBuilder stringBuilder, int left, int right, List<String> list) {
        if (left == 0 && right == 0) {
            list.add(stringBuilder.toString());
            return;
        }

        if (left > 0) {
            recursive(stringBuilder.append("("), left - 1, right, list);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }

        if (right > left) {
            recursive(stringBuilder.append(")"), left, right-1, list);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<List<String>> list = new ArrayList<>();
        list.add(Collections.singletonList(""));
        for ( int i = 1 ; i <= n ; i++) {
            List<String> newList = new ArrayList<>();
            for (int j = 0 ; j < i ; j++) {
                for (String first : list.get(j)) {
                    for (String second : list.get(i - 1 - j)) {
                        newList.add("("+first+")"+second);
                    }
                }
            }
            list.add(newList);
        }
        return list.get(list.size() - 1);
    }

    public static void main(String[] args) {
        int n = 2;
        List<String> result = new GenerateParenthesis().generateParenthesisRecursive(n);
        result.stream().forEach(System.out::println);
    }
}
