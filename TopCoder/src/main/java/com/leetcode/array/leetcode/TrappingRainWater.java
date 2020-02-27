package com.leetcode.array.leetcode;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/trapping-rain-water/
public class TrappingRainWater {
    public int trap(int[] height) {
        int a = 0;
        int b = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int result = 0;
        while ( a < b) {
            leftMax = Math.max(leftMax, height[a]);
            rightMax = Math.max(rightMax, height[b]);

            if (leftMax < rightMax) {
                result += leftMax - height[a];
                a++;
            } else {
                result += rightMax - height[b];
                b--;
            }
        }
        return result;

    }
    /*
    public int trap(int[] height) {
        int result = recursive(0, height.length - 1, height);
        return result;
    }

    private int recursive(int low, int high, int[] height) {
        if (low >= high) {
            return 0;
        }
        int maxIndex = getMaxIndex(height, low, high);
        int minIndex = getMinIndex(height, low, maxIndex);
        if (minIndex == maxIndex) {
            return 0;
        }
        System.out.println("minIndex " + minIndex +" maxIndex " + maxIndex);
        int result = 0;

        if (height[minIndex] > height[maxIndex]) {
            for (int i = minIndex+1 ; i < maxIndex; i++) {
                if (height[i] < height[maxIndex]) {
                    result += height[maxIndex] - height[i];
                }
            }

        } else {
            for (int i = minIndex+1 ; i < maxIndex; i++) {
                result += height[minIndex] - height[i];
            }
        }
        int leftSum = recursive(low, minIndex, height);
        int rightSum = recursive(maxIndex, high, height);
        System.out.println("low " + low +" high " + high);
        System.out.println("returning result "+ (result + leftSum + rightSum));
        return result + leftSum + rightSum;
    }

    private int getMinIndex(int[] height, int low, int maxIndex) {
        int max = height[maxIndex];
        int min = height[low];
        int minIndex = low;
        for ( int i = low ; i < maxIndex ; i++) {
            if (height[i] == 0) {
                continue;
            }
            if (height[i] <= max && height[i] >= min) {
                min = height[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private int getMaxIndex(int[] height, int low, int high) {
        int maxValue = height[high];
        int result = high;
        for (int i = high; i > low ; i--) {
            if (height[i] > maxValue) {
                maxValue = height[i];
                result = i;
            }
        }
        return result;
    }*/

    public static void main(String[] args) {
        int[] heights = {0,1,0,2,1,0,1,3,2,1,2,1};
        int res = new TrappingRainWater().trap(heights);
        System.out.println(res);
    }
}
