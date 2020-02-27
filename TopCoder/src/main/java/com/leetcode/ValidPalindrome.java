package com.leetcode;

//https://leetcode.com/problems/valid-palindrome/
public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() -1 ; i < j ; ) {
            if (!isAlphaNumeric(s, i)) {
                i++;
                continue;
            }

            if (!isAlphaNumeric(s, j)) {
                j--;
                continue;
            }
            int diff1 = getDiff(s.charAt(i));
            int diff2 = getDiff(s.charAt(j));
            if (diff1 != diff2) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

    private int getDiff(char charAt) {
        if (charAt >= 'A' && charAt <= 'Z') {
            return charAt - 'A';
        } else if (charAt >= 'a' && charAt <= 'z') {
            return charAt - 'a';
        } else if (charAt >= '0' && charAt <= '9') {
            return charAt + '0';
        }
        return 0;
    }

    private boolean isAlphaNumeric(String s, int i) {
        return (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') || (s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
               || s.charAt(i) >= '0' && s.charAt(i) <= '9';
    }

    public static void main(String[] args) {
        String str = " apG0i4maAs::sA0m4i0Gp0";
        System.out.println(new ValidPalindrome().isPalindrome(str));
    }
}
