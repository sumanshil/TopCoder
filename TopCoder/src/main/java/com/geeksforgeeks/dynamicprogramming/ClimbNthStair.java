package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 7/15/17.
 */
//http://www.geeksforgeeks.org/climb-n-th-stair-with-all-jumps-from-1-to-n-allowed-three-different-approaches/
public class ClimbNthStair {

    public void find (int n){
        int res = recursive(n);
        System.out.println(res);
    }

    private int recursive(int n) {
        if (n == 0){
            return 1;
        }
        if ( n == 1){
            return 1;
        }

        int result = 0;
        for ( int i = n-1 ; i >= 0 ; i--){
            result += recursive(i);
        }
        return result;
    }

    private void dp(int n){
        int[] arr = new int[n+1];
        arr[0] = 1;
        for ( int i = 1 ; i <= n ; i++ ){
            for (int j = 0 ; j < i ; j++){
                arr[i] += arr[j];
            }
        }
        System.out.println(arr[n]);
    }


    public static void main(String[] args) {
        int n = 3;
        new ClimbNthStair().dp(n);
    }
}
