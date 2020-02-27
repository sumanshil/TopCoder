package com.leetcode;

import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/additive-number/
public class AdditiveNumbers {

    public boolean isAdditiveNumber(String num) {
        boolean result = helper(0, num, new LinkedList<>());
        return result;
    }

    private boolean helper(int index, String num, List<String> list) {
        if (index == num.length()) {
            return true;
        }

        if (list.size()  >= 2) {
            long num1 = Long.parseLong(list.get(list.size() - 1));
            long num2 = Long.parseLong(list.get(list.size() - 2));
            long result = num1 + num2;
            long resultLength = (result + "").length();
            if (index + resultLength <= num.length()) {
                long num3 = Long.parseLong(num.substring(index, (int)(index + resultLength)));
                if (num1 + num2 == num3) {
                    list.add(num.substring(index, (int)(index + resultLength)));
                    if (helper((int)(index + resultLength), num, list)) {
                        return true;
                    } else {
                        list.remove(list.size()-1);
                    }
                }
            }
        } else {
            for (int L = 1 ; index + L < num.length() ; L++) {
                String str = num.substring(index, index + L);
                if (str.length() > 1 && str.startsWith("0")) {
                    continue;
                }
                list.add(str);
                if (helper(index + L, num, list)) {
                    return true;
                } else {
                    list.remove(list.size() - 1);
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        //String input = "112358";

        //String input = "199100199";
        //String input = "1023";
        String input = "198019823962";

        boolean result = new AdditiveNumbers().isAdditiveNumber(input);
        System.out.println(result);
    }
}
