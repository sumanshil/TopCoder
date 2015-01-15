package com.topcoder.problems;

public class FindingMaxRecursively {

    int[] arr = {5,4,3,2,1,8,9,7};
    
    public int findMax(int index){
        if (index >= arr.length){
            return 0;
        }
        
        int max = findMax(index+1);
        
        int max1 = arr[index];
        if (max1 > max)
            return max1;
        else
            return max;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        int result = new FindingMaxRecursively().findMax(0);
        System.out.println(result);
    }

}
