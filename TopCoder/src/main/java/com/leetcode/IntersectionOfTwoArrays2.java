package com.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/intersection-of-two-arrays-ii/
public class IntersectionOfTwoArrays2 {

    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();

        List<Integer> result = new LinkedList<>();

        for (int num : nums1) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }

        for (int num : nums2) {
            if (map.containsKey(num)) {
                result.add(num);
                int count = map.get(num);
                if (count == 1) {
                    map.remove(num);
                } else {
                    map.put(num, count - 1);
                }
            }
        }

        int[] retVal = new int[result.size()];
        int i = 0;
        for (Integer in : result) {
            retVal[i++] = in;
        }
        return retVal;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9,4,9,8,4};

        int[] result = new IntersectionOfTwoArrays2().intersect(nums1, nums2);
        for (int i : result) {
            System.out.println(i);
        }

    }
}
