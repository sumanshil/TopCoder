package com.leetcode.array.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

//https://leetcode.com/problems/jewels-and-stones/description/
public class JewelsAndStones {

    public int find(String J, String S) {
        Set<String> jewels = new HashSet<>();
        for ( int i = 0 ; i < J.length() ; i++) {
            jewels.add(J.charAt(i)+"");
        }

        int result = 0;
        Predicate<String> predicate = jewels::contains;
        for ( int i = 0 ; i < S.length() ; i++) {
            String s = S.charAt(i)+"";
            if (predicate.test(s)) {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String J = "aA";
        String S = "aAAbbbb";

        int result = new JewelsAndStones().find(J, S);
        System.out.println(result);
    }
}
