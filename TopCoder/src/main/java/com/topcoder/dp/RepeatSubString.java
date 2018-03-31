package com.topcoder.dp;

/**
 * Created by sshil on 9/24/2016.
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=14391
public class RepeatSubString {

    public int minimalModify(String str){
        if (str.length() == 1){
          //  System.out.println("Minimum changes "+1);
            return 1;
        }
        int secondStartIndex = str.length()/2;
        //int result = findResursive(str.substring(0, secondStartIndex), str.substring(secondStartIndex), 0);
        int result = Integer.MAX_VALUE;
        for (int i = 0 ; i < str.length() ; i++) {
            result = Math.min(result, dp(str.substring(0, i), str.substring(i, str.length())));
        }
        return result;
        //System.out.println(result);
    }

    private int findResursive(String str1, String str2, int operation) {
        if (str1.length() == 0 && str2.length() == 0){
            return operation;
        }
        if (str1.length() == 0 && str2.length() > 0){
            return operation+ str2.length();
        }
        if (str1.length() > 0 && str2.length() == 0){
            return operation+ str1.length();
        }
        if (str1.charAt(0) == str2.charAt(0)){
            return findResursive(str1.substring(1), str2.substring(1), operation);
        } else {
            // insert a new character in the first string
            int result1 =  findResursive(str1, str2.substring(1), operation+1);
            // insert a new character in the second string
            int result2 =  findResursive(str1.substring(1), str2, operation+1);
            // replace
            int result3 =  findResursive(str1.substring(1), str2.substring(1), operation+1);
            return Math.min(result1, Math.min(result2, result3));
        }

    }

    public int dp(String str1, String str2){
        int rowLength = str1.length()+1;
        int colLength = str2.length()+1;
        int[][] dp = new int[rowLength][colLength];
        dp[0][0] = 0;
        for (int i = 1 ; i < colLength ; i++){
            dp[0][i] = i;
        }

        for ( int i = 1 ; i < rowLength ; i++){
            dp[i][0] = i;
        }

        for ( int i = 1 ; i < rowLength ; i++) {
            for ( int j = 1 ; j < colLength ; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    int value1 = dp[i-1][j];
                    int value2 = dp[i][j-1];
                    int value3 = dp[i-1][j-1];
                    dp[i][j] = Math.min(value1, Math.min(value2, value3))+1;
                }
            }
        }
        return (dp[rowLength-1][colLength-1]);
    }

    public static void main(String[] args) {
        String str = "pkafkbeabccfjejkdgkaatcedaocgmecaapakfvbfgefr";
        new RepeatSubString().minimalModify(str);
    }
}
