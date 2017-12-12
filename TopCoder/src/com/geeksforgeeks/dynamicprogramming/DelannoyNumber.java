package com.geeksforgeeks.dynamicprogramming;


//http://www.geeksforgeeks.org/delannoy-number/
public class DelannoyNumber {

    public void find (int maxX, int maxY){
        dp(maxX, maxY);
    }


    private void dp(int maxX, int maxY){
        int dp[][] = new int[maxX+1][maxY+1];
        for (int i = 0 ;i <= maxX ; i++){
            dp[i][0] = 1;
        }
        for (int i = 0 ; i <= maxY ; i++){
            dp[0][i] = 1;
        }

        for (int i = 1 ; i <= maxX ; i++) {
            for (int j = 1 ; j <= maxY ; j++) {
                dp[i][j] = dp[i][j-1] + dp[i-1][j] + dp[i-1][j-1];
            }
        }
        System.out.println(dp[maxX][maxY]);
    }


    public static void main(String[] args) {
        int x = 3;
        int y = 3;
        new DelannoyNumber().find(x, y);
    }

}
