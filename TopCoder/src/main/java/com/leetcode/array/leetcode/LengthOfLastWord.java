package com.leetcode.array.leetcode;

public class LengthOfLastWord {

    public int lengthOfLastWord(String s) {
        int retVal = 0;
        for (int i = s.length() - 1  ; i >= 0 ; i--) {

            if (s.charAt(i) == ' ' && retVal > 0) {
                break;
            }
            retVal++;
        }
        return retVal;
    }

    public static void main(String[] args) {
        String s = "Hello World";
        int res = new LengthOfLastWord().lengthOfLastWord(s);
        System.out.println(res);
    }
}
