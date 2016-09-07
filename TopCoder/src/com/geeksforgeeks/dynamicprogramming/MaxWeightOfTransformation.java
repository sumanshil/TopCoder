package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 9/1/16.
 */
// http://www.geeksforgeeks.org/maximum-weight-transformation-of-a-given-string/
public class MaxWeightOfTransformation {
    public void find(String str){
        if (str.length() == 1){
            System.out.println(1);
            return;
        }
        int[] dp = new int[str.length()+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2 ; i < dp.length ; i++) {
            char currentChar = str.charAt(i-1);
            char prevChar = str.charAt(i-2);
            // don't toggle
            // consider pair
            int result1 = dp[i-2]+(currentChar != prevChar ? 4 : 0);
            // don't consider pair
            int result2 = dp[i-1] + 1;
            // toggle
            // consider pair
            char toggled = toggledChar(currentChar);
            int result3 = dp[i-2]+(toggled != prevChar ? 4 : 0)-1;
            int result4 = dp[i-1] - 1;
            dp[i] = Math.max(result1, Math.max(Math.max(result2, result3), result4));
        }
        System.out.println(dp[dp.length-1]);


    }

    private char toggledChar(char currentChar) {
        if (currentChar == 'A'){
            return 'B';
        } else {
            return 'A';
        }

    }

    public static void main(String[] args) {
        String str = "ABB";
        new MaxWeightOfTransformation().find(str);
    }
}
