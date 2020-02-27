package com.leetcode;

//https://leetcode.com/problems/excel-sheet-column-number/
public class ExcelSheetColumnNumber {

    public int titleToNumber(String s) {
        int length = s.length() - 1;
        int index = 0;
        int result = 0;
        while (length >= 0) {
            result += Math.pow(26, length) * ((s.charAt(index) - 'A') + 1);
            length--;
            index++;
        }
        return result;
    }

    public static void main(String[] args) {
        String str = "ZY";
        int result = new ExcelSheetColumnNumber().titleToNumber(str);
        System.out.println(result);
    }
}
