package com.geeksforgeeks.array;

public class MaximumSubArraySum {

    public int getMaxSubArraySum(int low, int high, int[] arr){
        if (low == high)
            return arr[low];
        
        int mid = (low + high)/2;
        
        int leftSum = getMaxSubArraySum(low, mid, arr);
        int rightSum = getMaxSubArraySum(mid+1, high, arr);
        
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for(int i = mid ; i >=0 ; i--){
            sum = sum + arr[i];
            if (sum > max){
                max = sum;
            }
        }
        int sum1 = max;
        max = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid+1; i< arr.length ; i++){
            sum = sum + arr[i];
            if (sum> max){
                max = sum;
            }
        }
        int sum2 = max;

        return Math.max(leftSum, Math.max(rightSum, (sum1+sum2)));
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
         //int arr[] = {2, 3, 4, 5, 7};
        int [] arr =  {-2, -5, 6, -2, -3, 1, 5, -6};
         int result = new MaximumSubArraySum().getMaxSubArraySum(0, arr.length-1, arr);
         System.out.println(result);
    }

}
