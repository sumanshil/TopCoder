package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 7/17/17.
 */
//http://www.geeksforgeeks.org/find-length-longest-subsequence-one-string-substring-another-string/
public class FindLengthOfSubSequenceOfOneString {

    public void find(String x, String y) {
        int result = recrusiveUtil(0, 0, x, y);
        System.out.println(result);
    }
    // m is length of x
    // n is length of y
    // dp[][]
    // dp[i][j] = maximum sub-sequence (0,i) which is sub string of (0, j)
    // if (i == j)
    // dp[i-1][j-1] + 1
    // else
    //
    private int recrusiveUtil(int xIndex, int yIndex, String x, String y) {
        return -1;
    }

    public static void main(String[] args) {
        String X = "ABCD";
        String Y = "AEECD";
        new FindLengthOfSubSequenceOfOneString().find(X, Y);
    }
}
