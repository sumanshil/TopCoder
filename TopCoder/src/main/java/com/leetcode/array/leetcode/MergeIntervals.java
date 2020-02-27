package com.leetcode.array.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/merge-intervals/
public class MergeIntervals {

    class Pair {
        int low;
        int max;

        Pair(int startVal, int endVal) {
            this.low = startVal;
            this.max = endVal;
        }
    }


    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[][]{};
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<Pair> pairList = new LinkedList<>();
        int startVal = intervals[0][0];
        int endValue = intervals[0][1];
        for (int i = 1 ; i < intervals.length ; i++) {
            if (intervals[i][0] <= endValue && intervals[i][1]>= endValue) {
                endValue = intervals[i][1];
            } else if (intervals[i][0] <= endValue && intervals[i][1] < endValue){
                continue;
            } else  {
                pairList.add(new Pair(startVal, endValue));
                startVal = intervals[i][0];
                endValue = intervals[i][1];
            }
        }
        pairList.add(new Pair(startVal, endValue));
        int[][] resultMatrix = new int[pairList.size()][2];
        int index = 0;
        for (Pair pair : pairList) {
            resultMatrix[index][0] = pair.low;
            resultMatrix[index++][1] = pair.max;
        }
        return resultMatrix;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 4}, {2, 3}};
        int[][] results = new MergeIntervals().merge(matrix);
        for (int i = 0 ; i < results.length ; i++) {
            System.out.println(results[i][0]+ " " + results[i][1]);
        }
    }

}
