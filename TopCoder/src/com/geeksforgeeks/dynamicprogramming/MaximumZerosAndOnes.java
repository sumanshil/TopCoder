package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/maximum-difference-zeros-ones-binary-string/
public class MaximumZerosAndOnes {

    public void find (String str) {
        int dp[][] = new int[str.length()][str.length()];

        for (int i = 0 ; i < str.length() ; i++){
            if (str.charAt(i) == '0'){
                dp[i][i] = 1;
            } else {
                dp[i][i] = -1;
            }
        }
        int max = Integer.MIN_VALUE;

        for (int i = 0  ; i + 1 < str.length() ; i++ ){
            if (allZeros(i, i+1, str)){
                dp[i][i+1] = 2;
                max = 2;
            } else if (allOnes(i, i+1, str)){
                dp[i][i+1] = -2;
            }
        }

        for (int L = 2 ; L < str.length() ; L++){
            for (int i = 0 ; i + L < str.length() ; i++) {
                int j = i + L;
                if (allOnes(i, j, str)) {
                    dp[i][j] = dp[i+1][j-1] -2;
                } else  if (allZeros(i, j, str)){
                    dp[i][j] = dp[i+1][j-1] + 2;
                    max = Math.max(max, dp[i][j]);
                } else {
                    dp[i][j] = dp[i+1][j-1];
                }
            }
        }

        System.out.println(max);
    }

    private boolean allOnes(int index1, int index2, String str) {
        return str.charAt(index1) == '1' && str.charAt(index2) == '1';
    }

    private boolean allZeros(int index1, int index2, String str) {
        return str.charAt(index1) == '0' && str.charAt(index2) == '0';
    }

    public static void main(String[] args) {
        String str = "11000010001";
        new MaximumZerosAndOnes().find(str);
    }

}
