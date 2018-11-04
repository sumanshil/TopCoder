package com.leetcode.String;

import java.util.Arrays;

//https://leetcode.com/problems/number-of-lines-to-write-string/description/
public class NumberOfLinesToWriteString {

    public int[] numberOfLines(int[] widths, String S) {
        int rowCount = 0;
        int spaceCount = 0;
        for (int i = 0 ; i < S.length() ; i++) {
            int index = S.charAt(i) - 'a';
            if (spaceCount + widths[index] > 100) {
                rowCount ++;
                spaceCount = widths[index];
            } else {
                spaceCount += widths[index];
            }
        }
        return new int[]{rowCount+1, spaceCount};

    }

    public static void main(String[] args) {
        int []widths = {4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
        String s = "bbbcccdddaaa";

        int[] result = new NumberOfLinesToWriteString().numberOfLines(widths, s);
        Arrays.stream(result).forEach(System.out::println);
    }

}
