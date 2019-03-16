package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfAPhone {

    static Map<String, String> map = new HashMap<>();
    static {
        map.put("2", "abc");
        map.put("3", "def");
        map.put("4", "ghi");
        map.put("5", "jkl");
        map.put("6", "mno");
        map.put("7", "pqrs");
        map.put("8", "tuv");
        map.put("9", "wxyz");
    }

    public List<String> letterCombinationsRecursive(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        if (digits.length() == 1) {
            List<String> result = new ArrayList<>();
            String rString = map.get(digits.charAt(0)+"");

            for ( int i = 0 ; i < rString.length() ; i++) {
                result.add(rString.charAt(i)+"");
            }
            return result;

        }

        String s1 = map.get(digits.charAt(0)+"");

        List<String> resultList = letterCombinationsRecursive(digits.length() > 0 ? digits.substring(1): null);

        List<String> newList = new ArrayList<>();
        for (int i = 0 ; i < s1.length() ; i++) {
            for (String s: resultList ) {
                newList.add(s1.charAt(i)+""+s);
            }
        }
        return newList;
    }


    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.trim().length() == 0) {
            return new ArrayList<>();
        }
        Map<String, String> map = new HashMap<>();
        map.put("2", "abc");
        map.put("3", "def");
        map.put("4", "ghi");
        map.put("5", "jkl");
        map.put("6", "mno");
        map.put("7", "pqrs");
        map.put("8", "tuv");
        map.put("9", "wxyz");


        List<String> resStr = new ArrayList<>();
        String rString = map.get(digits.charAt(0)+"");

        for ( int i = 0 ; i < rString.length() ; i++) {
            resStr.add(rString.charAt(i)+"");
        }

        for ( int i = 1 ; i < digits.length() ; i++) {
            rString = map.get(digits.charAt(i)+"");
            resStr = combination(resStr, rString);
        }

        return resStr;
    }

    private List<String> combination(List<String> resStr, String rString) {
        List<String> newResult = new ArrayList<>();

        for (String s : resStr) {
            for ( int i = 0 ; i < rString.length() ; i++) {
                newResult.add(s+rString.charAt(i));
            }
        }
        return newResult;
    }

    public static void main(String[] args) {
        List<String> res = new LetterCombinationsOfAPhone().letterCombinationsRecursive("23");
        res.stream().forEach(System.out::println);
    }
}
