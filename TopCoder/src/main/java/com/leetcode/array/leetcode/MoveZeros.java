package com.leetcode.array.leetcode;

//https://leetcode.com/problems/move-zeroes/description/
public class MoveZeros {

    public void moveZeroes(int[] nums) {
        int currentIndexToSwap = 0;
        int currentIndex = 0;
        int zeroCount = 0;
        while (currentIndex < nums.length) {
            if (nums[currentIndex] == 0) {
                zeroCount++;
                currentIndex++;
                continue;
            } else {
                if (currentIndex > currentIndexToSwap) {
                    swap(nums, currentIndex, currentIndexToSwap);
                    nums[currentIndex] = 0;
                    currentIndex++;
                    currentIndexToSwap++;
                } else if (currentIndex == currentIndexToSwap) {
                    currentIndex++;
                    currentIndexToSwap++;
                }
            }
        }

        int lastIndex = nums.length - 1;
        while (zeroCount > 0) {
            nums[lastIndex] = 0;
            lastIndex--;
            zeroCount--;
        }
    }

    private void swap(int[] nums, int currentIndex, int currentIndexToSwap) {
        int temp = nums[currentIndex];
        nums[currentIndex] = nums[currentIndexToSwap];
        nums[currentIndexToSwap] = temp;
    }

    public static void main(String[] args) {
        int nums[] = {0, 1, 0, 3, 12};
        new MoveZeros().moveZeroes(nums);
    }

}
