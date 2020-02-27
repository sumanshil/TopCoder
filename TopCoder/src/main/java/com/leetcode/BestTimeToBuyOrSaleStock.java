package com.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
public class BestTimeToBuyOrSaleStock {

    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int max = helper(0, prices.length - 1, prices, new HashMap<>());
        return max;
    }

    class Pair {
        int start;
        int end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Pair)) {
                return false;
            }
            Pair pair = (Pair) o;
            return start == pair.start &&
                   end == pair.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }

    private int helper(int start, int end, int[] prices, Map<Pair, Integer> map) {
        if (start > end) {
            return 0;
        }
        if (start == end) {
            return 0;
        }
        if (map.containsKey(new Pair(start, end))) {
            return map.get(new Pair(start, end));
        }
        int value1 = prices[end] > prices[start] ? prices[end] - prices[start] : 0;

        int maxValue = Integer.MIN_VALUE;
        for (int j = start + 1 ; j < end; j++ ) {
            int value2 = helper(start, j, prices, map);
            int value3 = helper(j, end, prices, map);
            int value4 = helper(start, j-1, prices, map);
            int value5 = helper(j+1, end, prices, map);

            maxValue = Math.max(Math.max(Math.max(value2, value3), value4 + value5), maxValue);
        }
        int value =  Math.max(value1, maxValue);
        map.put(new Pair(start, end), value);
        return value;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,0,2};
        int result = new BestTimeToBuyOrSaleStock().maxProfit(arr);
        System.out.println(result);
    }
}
