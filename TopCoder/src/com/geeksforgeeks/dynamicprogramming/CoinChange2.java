package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
public class CoinChange2 {

    public void find(int[] s, int p){
        int result = recursive(s.length, p, s);
        System.out.println(result);
    }

    private int recursive(int index, int point, int[] s) {
        if ( point == 0 ){
            return 1;
        }

        if (point < 0){
            return 0;
        }

        if (index <= 0 && index >= 1){
            return 0;
        }

        int result1 = recursive(index-1, point, s);
        int result2 = recursive(index, point-s[index-1], s);
        return result1 + result2;

    }

    public static void main(String[] args) {
        int N = 4;
        int[] s = {1, 2, 3};
        new CoinChange2().find(s, N);
    }
}
