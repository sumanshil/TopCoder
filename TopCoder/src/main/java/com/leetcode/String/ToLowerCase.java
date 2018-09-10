package com.leetcode.String;

import java.util.Arrays;

public class ToLowerCase {

    public String toLowerCase(String str) {
        StringBuilder stringBuilder = new StringBuilder(str.length());
        char[] arr = str.toCharArray();
        for (char c : arr) {
            if (c >= 'A' && c <= 'Z') {
                int newChar = ((int)c) + 32;
                String s = (char)newChar+"";
                stringBuilder.append(s);
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println((int)'A');
        System.out.println((int)'a');
        String input = "Hello";
        String res = new ToLowerCase().toLowerCase(input);
        System.out.println(res);
    }
}
