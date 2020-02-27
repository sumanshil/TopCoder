package com.leetcode;

//https://leetcode.com/problems/increasing-triplet-subsequence/
public class IncreasingTripletSubSequence {

    public boolean increasingTriplet(int[] nums) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int min3 = Integer.MAX_VALUE;

        for (int i = 0 ; i < nums.length ; i++) {
            if (min1 != Integer.MAX_VALUE && min2 != Integer.MAX_VALUE) {
                if (nums[i] > min1 && nums[i] > min2) {
                    return true;
                }
            }

            if (min1 == Integer.MAX_VALUE) {
                min1 = nums[i];
            } else if (min2 == Integer.MAX_VALUE && nums[i] > min1) {
                min2 = nums[i];
            } else if ( nums[i] < min2 && nums[i] > min1) {
                min2 = nums[i];
            }  else if (nums[i] < min1 && nums[i] < min3) {
                min3 = nums[i];
            } else if (nums[i] > min3) {
                min1 = min3;
                min2 = nums[i];
                min3 = Integer.MAX_VALUE;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {5,4,3,2,1};
        boolean res = new IncreasingTripletSubSequence().increasingTriplet(arr);
        System.out.println(res);

    }
}
