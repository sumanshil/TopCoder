package com.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

//https://leetcode.com/problems/generate-parentheses/
public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<List<String>> result = helper(n);
        List<String> retVal = new LinkedList<>();
        for (List<String> list : result) {
            String str = String.join("", list);
            if (!retVal.contains(str)) {
                retVal.add(str);
            }
        }
        return retVal;
    }

    private List<List<String>> helper(int n) {
        if (n == 1) {
            List<List<String>> list = new LinkedList<>();
            List<String> list1 = new LinkedList<>(Arrays.asList("(", ")"));
            list.add(list1);
            return list;
        }

        List<List<String>> retVal = helper(n-1);

        List<List<String>> result = new LinkedList<>();

        for (List<String> lst : retVal ) {
            List<String> newList = new LinkedList<>(lst);
            newList.add(0,")");
            newList.add(0, "(");
            result.add(newList);
        }

        for (List<String> lst : retVal ) {

            for (int i = 0 ; i < lst.size() ; i++) {
                if (lst.get(i).equals("(")) {
                    List<String> newList = new LinkedList<>(lst);
                    newList.add(i+1, ")");
                    newList.add(i+1, "(");
                    result.add(newList);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 4;
        List<String> list = new GenerateParenthesis().generateParenthesis(n);
        System.out.println(list);
    }
}
