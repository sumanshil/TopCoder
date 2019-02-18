package com.leetcode.array.leetcode;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/next-greater-element-i/
public class NextGreaterElement {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums2.length == 0 || nums1.length == 0) {
            return new int[0];
        }
        Map<Integer, Integer> mapToPosition = new HashMap<>();
        int [] nextGreater = new int[nums2.length];
        nextGreater[nums2.length-1] = -1;
        int result[] = new int[nums1.length];
        mapToPosition.put(nums2[nums2.length-1], nums2.length-1);
        for ( int i = nums2.length-2 ; i >= 0 ; i--) {
            mapToPosition.put(nums2[i], i);
            nextGreater[i] = -1;
            for ( int j= i + 1 ; j < nums2.length ; j++) {
                if (nums2[j] > nums2[i]) {
                    nextGreater[i] = nums2[j];
                    break;
                } else if (nextGreater[j] > nums2[i]) {
                    nextGreater[i] = nextGreater[j];
                    break;
                }
            }
        }

        for (int i = 0 ; i < nums1.length ; i++) {
            result[i] = nextGreater[mapToPosition.get(nums1[i])];
        }
        return result;
    }

    public static void main(String[] args) {
        int nums1[] = {2,4};
        int nums2[] = {1,2,3,4};
        int result[] = new NextGreaterElement().nextGreaterElement(nums1, nums2);
        for ( int i = 0 ; i < result.length ; i++) {
            System.out.println(result[i]);
        }

    }
}
