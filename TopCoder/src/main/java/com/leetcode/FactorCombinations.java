package com.leetcode;

import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/factor-combinations/
public class FactorCombinations {

    /**
    public List<List<Integer>> getFactors(int n) {
        if (n == 1) {
            return new LinkedList<>();
        }
        List<List<Integer>> result = new LinkedList<>();

        List<Integer> list = new LinkedList<>();

        recursive(2, result, list, n, n);

        return result;
    }

    private void recursive(int startIndex, List<List<Integer>> result, List<Integer> list,
                           int n, int originalNumber) {
        if ( n == 1) {
            result.add(new LinkedList<>(list));
            return;
        }
        if (n < startIndex) {
            return;
        }
        if (n == startIndex) {
            list.add(startIndex);
            result.add(new LinkedList<>(list));
            list.remove(list.size() - 1);
            return;
        }


        for (int i = startIndex ; i <=  originalNumber/2; i++) {
            if (i <= n && n % i == 0) {
                 list.add(i);
                 recursive(i, result, list, n/i, originalNumber);
                 list.remove(list.size()-1);
            }
        }

    }
     **/
    public List<List<Integer>> getFactors(int n) {
        if (n <= 3) {
            return new LinkedList<>();
        }
        List<List<Integer>> result = new LinkedList<>();

        List<Integer> list = new LinkedList<>();

        recursive(2, result, list, n);

        return result;
    }

    private void recursive(int curr, List<List<Integer>> result, List<Integer> list, int val) {
        for ( int i = curr ; i * i <= val ; i++) {
            if (val % i != 0) {
                continue;
            }
            int remainder = val / i;
            list.add(i);
            list.add(remainder);
            result.add(new LinkedList<>(list));
            list.remove(list.size() - 1);
            recursive(i, result, list, remainder);
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args) {
        int n = 32;
        List<List<Integer>> result = new FactorCombinations().getFactors(n);
        System.out.println(result);
    }
}
