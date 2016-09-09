package com.geeksforgeeks.dynamicprogramming;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sshil on 9/1/16.
 */
//http://www.geeksforgeeks.org/count-total-number-of-n-digit-numbers-such-that-the-difference-between-the-sum-of-even-digits-and-odd-digits-is-1/
public class CountTotalNumberOfNDigitNumbers {

    private int result = 0;
    private List<Integer> oddList = new LinkedList<>();
    private List<Integer> evenList = new LinkedList<>();
    public void find(int n){
        recursive(0, n, 0, 0);
        System.out.println(result);

    }

    private void recursive(int currentIndex, int maxIndex, int oddSum, int evenSum) {
        if (currentIndex == maxIndex) {
            if ((evenSum-oddSum) == 1){
                System.out.println("================");
                System.out.println("\nOdd List");
                oddList.stream().forEach(System.out::print);
                System.out.println("\nEven List");
                evenList.stream().forEach(System.out::print);
                result++;
            }
            return;
        }
        for ( int i = 0 ; i <= 9 ; i++) {
            if (currentIndex == 0 && i == 0){
                continue;
            }
            if (currentIndex % 2 == 0){
                evenList.add(i);
                recursive(currentIndex+1, maxIndex, oddSum, evenSum+i);
                evenList.remove(evenList.size()-1);
            } else {
                oddList.add(i);
                recursive(currentIndex+1, maxIndex, oddSum+i, evenSum);
                oddList.remove(oddList.size()-1);
            }
        }
    }

    private int[][][][] dp = new int[50][1000][1000][2];
    public void find1(int n) {
        for ( int i = 0 ; i < 50 ; i++) {
            for (int j = 0 ; j < 1000 ; j++) {
                for (int k = 0 ; k < 1000 ; k++) {
                    for (int l = 0 ; l < 2 ; l++) {
                        dp[i][j][k][l] = -1;
                    }
                }
            }
        }
        long ans = 0L;
        int digits = 0;
        int eSum = 0;
        int oSum = 0;
        for (int i = 1 ; i < n ; i++) {
     //       ans += recursive1(digits+1, eSum+i, oSum, true, n );
        }


    }



    public static void main(String[] args) {
        new CountTotalNumberOfNDigitNumbers().find(4);
    }
}
