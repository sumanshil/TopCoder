package com.leetcode;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/unique-word-abbreviation/
public class ValidWordAbbr {
    private Set<String> set = new HashSet<>();

    public ValidWordAbbr(String[] dictionary) {
        for (String str : dictionary) {
            if (str.length() > 2) {
                String s1 = str.charAt(0)+"";
                String s2 = str.charAt(str.length()-1) + "";
                int num = str.length() - 2;
                set.add(s1 + num + s2);
            }
        }
    }

    public boolean isUnique(String word) {
        String abbr = (word.charAt(0)+"") + (word.length() - 2 )+ (word.charAt(word.length()-1)+"");
        return !set.contains(abbr);
    }

    public static void main(String[] args) {
        String[] arr = { "hello"};
        ValidWordAbbr abbr = new ValidWordAbbr(arr);
        System.out.println(abbr.isUnique("hello"));

    }
}
