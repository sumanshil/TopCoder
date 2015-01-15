package com.topcoder.problems.round13;

public class Array {

    public void getAllSum(int[] arr, int index, int sum){
        if (index == arr.length){
            System.out.println(sum);
            return;
        }
        
        getAllSum(arr, index+1, sum);
        
        getAllSum(arr, index+1, sum+arr[index]);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        new Array().getAllSum(arr, 0, 0);
    }

}
