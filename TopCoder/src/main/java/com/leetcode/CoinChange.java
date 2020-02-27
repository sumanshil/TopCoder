package com.leetcode;

import java.util.Arrays;

//https://leetcode.com/problems/coin-change/
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] arr = new int[amount + 1];
        for (int i = 1 ; i <= amount ; i++) {
            arr[i] = -1;
        }
        for (int i = 1 ; i <= amount ; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0 ; j < coins.length ; j++) {
                if (coins[j] <= i && arr[i - coins[j]] != -1) {
                    min = Math.min(min, 1 + arr[i - coins[j]]);
                    arr[i] = min;
                } else if (coins[j] > i){
                    break;
                }
            }
        }
        return arr[amount];
    }

    public static void main(String[] args) {
//        int[] coins = {1, 2, 5};
//        int amount = 11;
//        int[] coins = {2};
//        int amount = 3;
        int[] coins = {186, 419, 83, 408};
        int amount = 6249;

        int res = new CoinChange().coinChange(coins, amount);
        System.out.println(res);
    }
}
