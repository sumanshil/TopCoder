package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 7/6/17.
 */
//http://www.geeksforgeeks.org/painting-fence-algorithm/
public class PaintingFenceAlgo {

    // n == 1
    // diff = k
    // same = 0

    // n == 2
    // diff = (k * (k-1))
    // same = k

    // n == 3
    // diff = (k+ (k*(k-1)))*(k-1)
    // same = (k*(k-1)) (c" != c)
    // total = same(i) + diff(i)
    //       = diff(i) + diff(i-1)
    //diff(i) = (k + diff(i-1))(k-1)
    //        = (diff(i-2) + diff(i-1))(k-1)
    //        = (total(i-1))(k-1)

    //
    //
    public void find(int n , int k){
       int[] dp = new int[n+1];
       dp[1] = k;

    }


    public static void main(String[] args) {
        int n = 3;
        int k = 2;
    }
}
