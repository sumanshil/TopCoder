package com.leetcode;

//https://leetcode.com/problems/excel-sheet-column-title/discuss/424757/Java-solution%3A-beats-100-runtime-and-100-memory-usages
public class ConvertToTitle {

    public String convertToTitle(int n) {
        if ( n <= 0) {
            return "";
        }
        int lastChar = (n-1)%26;
        String prefix = convertToTitle((n-1)/26);
        return prefix + (char)(lastChar + 65);
    }

    public static void main(String[] args) {
        int i = 1;
        String result = new ConvertToTitle().convertToTitle(i);
        System.out.println(result);
        System.out.println(26*26);
    }
}
