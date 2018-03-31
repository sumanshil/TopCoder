package com.geeksforgeeks.dynamicprogramming;

//https://leetcode.com/problems/regular-expression-matching/description/
public class RegularExpressionMathcing {

    //"aab", "c*a*b"
    public boolean find(String n1, String n2){
        int xLength = n1.length() + 1;
        int yLength = n2.length() + 1;
        boolean[][] dp = new boolean[xLength][yLength];

        dp[0][0] = true;

        for ( int i = 1 ; i < xLength ; i++){
            dp[i][0] = false;
        }

        for ( int i = 1; i < yLength ; i++){
            dp[0][i] = false;
        }


        for (int i = 1; i < xLength ; i++) {
            for ( int j = 1; j < yLength ; j++) {
                if (n1.charAt(i-1) == n2.charAt(j-1)) {
                    dp[i][j] = !dp[i-1][j-1];
                } else if (n2.charAt(j-1) == '.') {
                    dp[i][j] = dp[i-1][j-1];
                } else  if (n2.charAt(j-1) == '*') {
                    if (j-2 >= 0) {
                        dp[i][j] = dp[i-1][j] || dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[xLength-1][yLength-1];
    }

    public boolean recursive(String n1, String n2){
        if (n1.length() == 0 && n2.length() == 0){
            return true;
        }
        if (n1.length() == 1 && n2.length() == 1){
            if (n2.charAt(0) == '*' || n2.charAt(0) == '.'){
                return true;
            }
            if (!n1.equals(n2)){
                return false;
            }
            return true;
        }

        if (n1.length() == 1 && n2.length() == 2){
            if (n2.charAt(1) == '*'){
                return true;
            }
            return false;
        }

        if ((n1.length() > 0 && n2.length() == 0) || (n2.length() > 0 && n1.length() == 0)){
            return false;
        }
        if (n1.charAt(0) == n2.charAt(0)){
            if (n2.length() > 2 && n2.charAt(1) == '*') {
                return recursive(n1.substring(1), n2.substring(2));
            } else {
                return recursive(n1.substring(1), n2.substring(1));
            }
        } else if (n2.charAt(0) == '.'){
            return  recursive(n1.substring(1), n2.substring(1));
        } else {
            if (n2.length() > 1 && n2.charAt(1) == '*'){
                boolean result1 = recursive(n1.substring(1), n2.substring(2));
                boolean result2 = recursive(n1, n2.substring(2));
                return result1 || result2;
            } else {
                return false;
            }
        }
    }


    public static void main(String[] args) {
        String str1 = "aab";
        String str2 = "c*a*b";

        boolean result = new RegularExpressionMathcing().find(str1, str2);
        System.out.println(result);
    }
}
