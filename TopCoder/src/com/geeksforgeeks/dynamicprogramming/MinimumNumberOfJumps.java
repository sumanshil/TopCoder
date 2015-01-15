package com.geeksforgeeks.dynamicprogramming;

public class MinimumNumberOfJumps {

    public int minJump(int[] arr, int index){
        if (index == arr.length - 1){
            return 0;
        }        
        
        if ((index + arr[index]) >= (arr.length -1 )){
            return 1;
        }
        if (arr[index] == 0){
            return Integer.MAX_VALUE;
        }
        
        int min = Integer.MAX_VALUE;
        
        for(int j = index; j < arr.length && j < index+arr[index] ; ){
            int min1 = minJump(arr, ++j);
            if (min1 == Integer.MAX_VALUE){
                if (min1 < min){
                    min = min1;
                }
            } else {
                min1 = min1+1;
                if (min1 < min){
                    min = min1;
                }
            }
        }        
        return min;
    }
    
    public int minJumpDynamic(int[] arr, int index){
        int[] table = new int[arr.length];
        table[arr.length-1] = 0;
        
        for (int i = arr.length -2 ; i>=0; i--){
            if ((i + arr[i]) >= (arr.length -1 )){
                table[i] = 1;
            } else {
                int min = Integer.MAX_VALUE;
                for(int j = i+1 ; j < arr.length && j  <= i+arr[i]; j++){
                    if (table[j] < min){
                        min = table[j];
                    }
                }
                if (min != Integer.MAX_VALUE){
                    table[i] = min+1;
                } else {
                    table[i] = min;
                }
            }
            
        }        
        return table[0];
    }
    
    
    
    /**
     * @param args
     */
    public static void main(String[] args) {
       // int[] arr = {1,2,3,4};
        int[] arr = {1, 3, 6, 1, 0, 9};
        //int[] arr = {1, 3, 6, 3, 2, 3, 6, 8, 9, 5};
        int result = new MinimumNumberOfJumps().minJumpDynamic(arr, 0);
        System.out.println(result);
    }

}
