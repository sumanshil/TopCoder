package com.leetcode.array.leetcode;

//https://leetcode.com/problems/container-with-most-water/discuss/6100/simple-and-clear-proofexplanation
public class MaxArea {

    public int maxArea(int[] heights) {
        int left = 0;
        int right = heights.length-1;

        int maxArea = Integer.MIN_VALUE;
        int maxHeight = Integer.MIN_VALUE;

        while (left < right) {
            if (heights[left] > maxHeight && heights[right] > maxHeight) {
                maxHeight = Math.min(heights[left], heights[right]);
                maxArea = (right-left)*Math.min(heights[left], heights[right]);
            }

            if (heights[left] == heights[right]) {
                left++;
                right--;
            } else if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {

    }
}
