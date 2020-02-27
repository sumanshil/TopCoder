package com.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

//https://leetcode.com/problems/sliding-window-maximum/
public class SlidingWindowMaximum {

    /*
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[]{};
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k, (i1, i2) -> {
            return i2 - i1;
        });
        int index= 0;
        List<Integer> list = new LinkedList<>();
        int[] result = new int[nums.length - k + 1];
        for (int i = 0 ; i < k ; i++) {
            priorityQueue.add(nums[i]);
        }
        //list.add(priorityQueue.peek());
        result[index++] = priorityQueue.peek();

        for (int i = k ; i < nums.length ; i++) {
            priorityQueue.remove(nums[i-k]);
            priorityQueue.add(nums[i]);
            //list.add(priorityQueue.peek());
            result[index++] = priorityQueue.peek();
        }
        //int[] result = new int[list.size()];
        //for (Integer res : list) {
        //    result[index++] = res;
        //}
        return result;
    }
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[]{};
        }

        int maxValueInWindow = Integer.MIN_VALUE;
        int[] result = new int[nums.length - k + 1];
        for (int i = 0 ; i < k ; i++) {
            maxValueInWindow = Math.max(nums[i], maxValueInWindow);
        }
        int index = 0;
        result[index++] = maxValueInWindow;
        for (int i = k; i < nums.length ;i++) {

            int outGoingNumber = nums[(i-k)];
            int inComingNumber = nums[i];

            if (inComingNumber > maxValueInWindow) {
                maxValueInWindow = inComingNumber;
                result[index++] = maxValueInWindow;
            } else if ( outGoingNumber >= maxValueInWindow) {
                maxValueInWindow = Integer.MIN_VALUE;
                for (int j = i - k + 1 ; j <= i ; j++) {
                    maxValueInWindow = Math.max(maxValueInWindow, nums[j]);
                }
                result[index++] = maxValueInWindow;
            } else {
                result[index++] = maxValueInWindow;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, -1};
        int k = 1;
        int[] res = new SlidingWindowMaximum().maxSlidingWindow(arr, k);
        for (int i : res) {
            System.out.println(i);
        }
    }
}
