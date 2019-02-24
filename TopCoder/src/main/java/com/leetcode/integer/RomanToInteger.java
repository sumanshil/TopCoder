package com.leetcode.integer;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/roman-to-integer/
public class RomanToInteger {

    public int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);
        int result = 0;

        for ( int i = 0 ; i < s.length() ; i++) {

            if (i + 1 < s.length() && map.containsKey(s.substring(i,i+2))) {
                result = result + map.get(s.substring(i, i+2));
                i++;
            } else {
                result = result + map.get(s.substring(i, i+1));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String input = "MCMXCIV";
        int res = new RomanToInteger().romanToInt(input);
        System.out.println(res);
    }

}
