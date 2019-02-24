package com.leetcode.integer;

import java.util.Arrays;

//https://leetcode.com/problems/longest-common-prefix/
public class LongestCommonPrefix {

    public String longestCommonPrefix1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String res = "";
        for (int i = 0 ; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1 ; j < strs.length ; j++) {
                if ( i > strs[j].length() || c != strs[j].charAt(i)) {
                    return res;
                }
            }
            res = res + c;
        }
        return res;

    }


    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        int arr[] = new int[strs[0].length()];
        String pattern = strs[0];
        Arrays.fill(arr, 1);
        for ( int i = 1 ; i < strs.length ; i++)  {
            for (int j = 0 ; j < strs[i].length() && j < pattern.length() ; j++) {
                if (strs[i].charAt(j) != pattern.charAt(j)) {
                    arr[j] = 0;
                    break;
                }
            }
            if (strs[i].length() < pattern.length()) {
                arr[strs[i].length()] = 0;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for ( int i = 0 ; i < arr.length ; i++) {
            if (arr[i] == 1) {
                stringBuilder.append(pattern.charAt(i));
            } else {
                break;
            }
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        String[] arr = {"flower","flow","flight"};
        String result = new LongestCommonPrefix().longestCommonPrefix(arr);
        System.out.println(result);
    }
}
