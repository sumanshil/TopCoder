package com.geeksforgeeks.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

//http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
public class CoinChange {

    public void change(int[] arr, int number){
        for(int i = 0 ; i < arr.length ; i++){
            util(number, i, arr);
        }
    }
    List<Integer> list = new ArrayList<Integer>();
    private void util(int number, int index, int[] arr) {
        if (index == arr.length ){
            return;
        }
        
        if (arr[index]<= number){
            list.add(arr[index]);
            number = number - arr[index];
        }

        if (number==0){
            for(Integer i : list){
                System.out.print(i+" ");
            }
            System.out.println();
        }

        for(int i = index; i< arr.length ; i++){
            if (number >= arr[i])
                util(number, i, arr);
        }
        list.remove(list.size()-1);
    }
    
    
    public int countRecursive(int[] arr, int n){
      int result =   recursiveUtil(arr, n, arr.length);
      return result;
    }
    
    private int recursiveUtil(int[] arr, int n, int m) {
        // If n is 0 then there is 1 solution (do not include any coin)
        if (n == 0){
            return 1;
        }
        
        if (n <= 0){
            return 0;
        }
        
        if (m<=0 && n >=1)
            return 0;
        
        int countWithout = recursiveUtil(arr, n, m-1);
        int countWith = recursiveUtil(arr, n-arr[m-1], m);
        return countWith+countWithout;
    }


    private int countDynamic(int[] arr, int n){
        int j, x, y;
        
        int[][] table = new int[n+1][arr.length];
        for(int i = 0 ; i < arr.length ; i++){
            table[0][i] = 1;
        }
        
        for(int i = 1; i <n+1;i++){
            for(j = 0 ; j < arr.length; j++){
                // Count of solutions including S[j]
                x = (i-arr[j]>=0)? table[i - arr[j]][j]: 0;
                // Count of solutions excluding S[j]
                y = (j >= 1)? table[i][j-1]: 0;
                // total count
                table[i][j] = x + y;                
            }
        }
        return table[n][arr.length-1];
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        //int n = 10;
        //int[] arr = {2, 5, 3, 6};
        int n = 4;
        int[] arr = {1,2,3};
        int result = new CoinChange().countDynamic(arr, n);
        System.out.println(result);
    }

}
