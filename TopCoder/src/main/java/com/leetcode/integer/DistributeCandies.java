package com.leetcode.integer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class DistributeCandies {

    public int distributeCandies(int[] candies) {
        int max = candies.length/2;
        Set<Integer> set = new HashSet<>();
        for (int i = 0 ; i < candies.length ; i++) {
            set.add(candies[i]);
        }
        if (set.size() < max) {
            return set.size();
        }
        return max;
    }

    public static void main(String[] args) {
        int[] candies = {1,1,1,1,2,2,2,3,3,3};
        int result = new DistributeCandies().distributeCandies(candies);
        System.out.println(result);
    }
}
