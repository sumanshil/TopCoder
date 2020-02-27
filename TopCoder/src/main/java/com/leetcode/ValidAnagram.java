package com.leetcode;

//https://leetcode.com/problems/valid-anagram/
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) {
            return false;
        }

        if (s.length() != t.length()) {
            return false;
        }

        int[] arr = new int[26];
        for ( int i = 0 ; i < s.length() ; i++) {
            int i1 = s.charAt(i) - 'a';
            arr[i1] = arr[i1] + 1 ;
        }

        for (int i = 0 ; i < t.length() ; i++) {
            int i1 = s.charAt(i) - 'a';
            if (arr[i1] == 0) {
                return false;
            }
            arr[i1] = arr[i1] - 1 ;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println(new ValidAnagram().isAnagram(s, t));
    }
}
