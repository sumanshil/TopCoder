package com.leetcode.array.leetcode;

//https://leetcode.com/problems/container-with-most-water/
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int low = 0;
        int high = height.length - 1;
        int max = Integer.MIN_VALUE;

        while (low < high) {
            max = Math.max(max, (high - low) * Math.min(height[low], height[high]));
            if (height[low] < height[high]) {
                low++;
            } else {
                high--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1,8,6,2,5,4,8,3,7};
        int result = new ContainerWithMostWater().maxArea(arr);
        System.out.println(result);
    }

}
