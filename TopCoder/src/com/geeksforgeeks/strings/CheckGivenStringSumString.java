package com.geeksforgeeks.strings;

//http://www.geeksforgeeks.org/check-given-string-sum-string/
public class CheckGivenStringSumString {


    public boolean check(String str){

        int n = str.length();
        for ( int i = 1; i < n ; i++) {
            for ( int j = 1; i+j < n ; j++) {
                if (checkSumUtil(str, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkSumUtil(String str, int begin, int len1, int len2) {
        String s1 = str.substring(begin, begin+len1);
        String s2 = str.substring(begin+len1, begin + len1 + len2);

        int sum = Integer.parseInt(s1) + Integer.parseInt(s2);
        String sumStr = Integer.toString(sum);

        if (begin + len1 + len2 + sumStr.length() > str.length()) {
            return false;
        }


        String thirdSubString = str.substring(begin + len1 + len2, begin + len1 + len2 + sumStr.length());
        if (!thirdSubString.equals(sumStr)){
            return false;
        } else if ((begin + len1 -1) + len2 + sumStr.length() == str.length()-1) {
            return true;
        }

        return checkSumUtil(str, begin + len1, len2, sumStr.length());
    }

    public static void main(String[] args) {
        String str = "1212243660";
        if (new CheckGivenStringSumString().check(str)) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }
    }
}
