package com.leetcode.integer;

//https://leetcode.com/problems/string-to-integer-atoi/
public class MyAtoi {

    public int myAtoi(String str) {
        if (str.trim().length() == 0) {
            return 0;
        }
        if (str.trim().length() == 1 && !isDigit(str, 0)) {
            return 0;
        }
        boolean isNegative = false;
        boolean isFirstCharacter = false;
        String resultStr = "";
        Double result = 0.0;
        for ( int i = 0 ; i < str.length() ; i++) {
            if (str.charAt(i) == '-' && !isFirstCharacter) {
                isFirstCharacter = true;
                isNegative = true;
            }

            if (!isFirstCharacter && !isDigit(str, i) && str.charAt(i) != ' ') {
                return 0;
            }

            if (isDigit(str, i) || str.charAt(i) == '.') {
                resultStr += (str.charAt(i)+"");
                if (!isFirstCharacter){
                    isFirstCharacter = true;
                }
            }
        }
        if (resultStr.trim().length() == 1 && !isDigit(resultStr, 0)) {
            return 0;
        }
        result = Double.parseDouble(resultStr);
        if (isNegative) {
            result = -result;
        }
        if (result >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (result <= Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return result.intValue();
    }

    private boolean isDigit(String str, int i) {
        return str.charAt(i) >= '0' && str.charAt(i) <= '9';
    }

    public static void main(String[] args) {
        String number = "-";
        int res = new MyAtoi().myAtoi(number);
        System.out.println(res);
    }

}
