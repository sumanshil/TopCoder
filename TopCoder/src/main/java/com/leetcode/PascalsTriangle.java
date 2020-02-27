package com.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/pascals-triangle/
public class PascalsTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new LinkedList<>();
        for (int i = 0 ; i < numRows ; i++) {
            if (list.size() == 0) {
                list.add(new LinkedList<>(Arrays.asList(1)));
            } else {
                List<Integer> lastList = list.get(list.size()-1);
                int newLength = lastList.size() + 1;
                List<Integer> newList = new LinkedList<>();

                for (int j = 0 ; j < newLength ; j++) {
                    int number1 = 0;
                    int number2 = 0;
                    if (j == lastList.size()) {
                        number2 = 0;
                    } else {
                        number2 = lastList.get(j);
                    }

                    if (j - 1 < 0) {
                        number1 = 0;
                    } else {
                        number1 = lastList.get(j-1);
                    }
                    newList.add(number1 + number2);
                }
                list.add(newList);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int n = 5;
        List<List<Integer>> result = new PascalsTriangle().generate(n);
        System.out.println(result);
    }
}
