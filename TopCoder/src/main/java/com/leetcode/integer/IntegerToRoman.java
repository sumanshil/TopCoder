package com.leetcode.integer;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/integer-to-roman/
public class IntegerToRoman {


    public String intToRoman1(int num) {
        int[] nums = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] codes = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = nums.length - 1; i >= 0; i--) {
                while (num >= nums[i]) {
                    stringBuilder.append(codes[i]);
                    num = num - nums[i];
                }
        }
        return stringBuilder.toString();
    }


    public String intToRoman(int num) {
        Map<Integer, String> map = new HashMap<>();
        map.put( 1,  "I");
        map.put( 4, "IV");
        map.put( 5,  "V");
        map.put( 9, "IX" );
        map.put( 10, "X");
        map.put( 40, "XL" );
        map.put( 50, "L");
        map.put( 90, "XC" );
        map.put( 100, "C");
        map.put( 400, "CD" );
        map.put( 500, "D");
        map.put( 900, "CM" );
        map.put( 1000, "M");

        String res = "";
        while(num > 0) {
            if (num>= 1000 && num <= 3999) {
                res = res + "M";
                num = num - 1000;
            } else if (num >= 900 && num < 1000) {
                res = res + "CM";
                num = num - 900;
            } else if (num >= 500 && num < 900) {
                res = res + "D";
                num = num - 500;
            } else if (num >= 400 && num < 500) {
                res = res + "CD";
                num = num - 400;
            } else if (num >= 100 && num < 400) {
                res = res + "C";
                num = num - 100;
            } else if (num >= 90 && num < 100) {
                res = res + "XC";
                num = num - 90;
            } else if (num >= 50 && num < 90) {
                res = res + "L";
                num = num - 50;
            } else if (num >= 40 && num < 50) {
                res = res + "XL";
                num = num - 40;
            } else if (num >= 10 && num < 40) {
                res = res + "X";
                num = num - 40;
            } else if (num == 9) {
                res = res + "IX";
                num = num - 9;
            } else if (num >= 5 && num < 9) {
                res = res + "V";
                num = num - 5;
            } else if (num  == 4) {
                res = res + "IV";
                num = num - 4;
            } else if (num >= 1 && num < 4) {
                res = res + "I";
                num = num - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        IntegerToRoman integerToRoman = new IntegerToRoman();
        String res = integerToRoman.intToRoman1(3999);
        System.out.println(res);
        res = integerToRoman.intToRoman(3999);
        System.out.println(res);
    }

}
