package com.leetcode.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/palindrome-partitioning/
public class PartitionStringWithPalindromSubString {
    private boolean dp[][];
    public List<List<String>> partition(String s) {
        dp = new boolean[s.length()][s.length()];

        for (int i = 0 ; i < s.length() ; i++) {
            dp[i][i] = true;
        }

        for (int length = 1 ;  length < s.length() ; length++) {
            for (int i = 0 ; i < s.length() && i + length < s.length() ; i++) {
                int index1 = i;
                int index2 = i + length;
                if (length == 1) {
                    if (s.charAt(index1) == s.charAt(index2)) {
                        dp[index1][index2] = true;
                    }
                } else {
                    if (s.charAt(index1) == s.charAt(index2) && dp[index1 + 1][index2 - 1]) {
                        dp[index1][index2] = true;
                    }
                }
            }
        }
        List<List<String>> result = new LinkedList<>();
        List<String> current = new LinkedList<>();
        recursive(0, s, result, current);
        return result;
    }

    private void recursive(int index, String s, List<List<String>> result, List<String> current) {
        if (index >= s.length()) {
            result.add(new LinkedList<>(current));
            return;
        }

        for (int level = 0; index + level < s.length() ; level++) {
            int index1 = index;
            int index2 = index + level;
            if (dp[index1][index2]) {
                current.add(s.substring(index1, index2 + 1));
                recursive(index2 + 1, s, result, current);
                current.remove(current.size()-1);
            }
        }
    }
    /*
    private List<List<String>> recursive(int index, String input) {
        if (index == input.length()) {
            List<String> list1 = new LinkedList<>();
            List<List<String>> result = new LinkedList<>();
            result.add(list1);
            return result;
        }
        List<List<String>> result = new ArrayList<>();
        for (int length = 0 ; index + length < input.length() ; length++) {
            int i1 = index;
            int i2 = index + length;
            if (dp[i1][i2]) {
                String s1 = input.substring(i1, i2+1);
                List<List<String>> rest = recursive(index + length + 1, input);
                for (List<String> list1 : rest) {
                    list1.add(0, s1);
                    result.add(list1);
                }
            }
        }
        return result;

    }
     */

    public static void main(String[] args) {
        String input = "aabb";
        List<List<String>> result = new PartitionStringWithPalindromSubString().partition(input);
        System.out.println(result);
    }
}
