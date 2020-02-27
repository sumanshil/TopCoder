package com.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

//https://leetcode.com/problems/palindrome-permutation/
public class PalindromPartition1 {

    public boolean canPermutePalindrome(String s) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0 ; i < s.length() ; i++) {
            char c = s.charAt(i);
            int count = map.getOrDefault(c+"", 0);
            map.put(c+"", count + 1);
        }
        if (map.size() > 0) {
            long count = map.entrySet().stream().filter(e -> e.getValue() % 2 != 0).count();
            return  count == 1 || count == 0;
        }
        return true;
    }

    public static void main(String[] args) {
        String code = "carerac";
        boolean result = new PalindromPartition1().canPermutePalindrome(code);
        System.out.println(result);
    }
}
