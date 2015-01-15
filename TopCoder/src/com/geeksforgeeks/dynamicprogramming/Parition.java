package com.geeksforgeeks.dynamicprogramming;

public class Parition {

    private boolean isSubsetPresent(int[] arr){
        int sum = 0;
        for(int i = 0 ; i < arr.length ; i++){
            sum+=arr[i];
        }
        
        if (sum%2 != 0){
            return false;
        }
        sum = sum/2;
        boolean result = recursion(sum,arr, arr.length-1);
        return result;
    }
    private boolean recursion(int sum, int[] arr, int index) {
        if (sum ==0)
            return true;
        
        if (index == 0 && sum != 0){
            return false;
        }
        boolean without = recursion(sum, arr, index-1);
        boolean with = recursion(sum-arr[index], arr, index-1);
        return with || without;
    }
    
    public boolean isSubSetPresentDynamic(int[] arr){
        int sum = 0;
        int n = arr.length;
        for(int i = 0 ; i < arr.length ; i++){
            sum +=arr[i];
        }
        boolean[][] table = new boolean[sum/2+1][n+1];
        
        for(int i = 0 ; i <= n ; i++){
            table[0][i] = true;
        }
        
        for(int i = 1 ; i <=sum/2; i++){
            table[i][0] = false;
        }
        
        for(int i = 1 ;  i <= sum/2; i++){
            for(int j = 1; j <=n; j++){
                table[i][j] = table[i][j-1];
                if ( i >= arr[j-1]){
                    table[i][j]= table[i][j] || table[i-arr[j-1]][j-1];
                }
            }
        }
        return table[sum/2][n];
    }
    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {1,5,11,5};
        //int[] arr = {1,3,9};
        boolean result = new Parition().isSubSetPresentDynamic(arr);
        System.out.println(result);

    }

}
