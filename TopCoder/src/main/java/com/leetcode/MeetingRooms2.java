package com.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

//https://leetcode.com/problems/meeting-rooms-ii/
public class MeetingRooms2 {

    class Pair {
        int low;
        int high;
        Pair(int low, int high) {
            this.low = low;
            this.high = high;
        }
    }

    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Comparator<int[]> myComparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        };

        Arrays.sort(intervals, myComparator);
        int count = 0;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int[] interval : intervals) {
            int low = interval[0];
            int high = interval[1];
            if (priorityQueue.isEmpty() || low < priorityQueue.peek()) {
                count++;
                priorityQueue.add(high);
            } else {
                priorityQueue.poll();
                priorityQueue.add(high);
            }
        }
        return count;
    }

    public static void main(String[] args) {
//        int[][] matrix =
//                {{20, 45},{12, 13}, {2, 50}, {14, 20}, {3, 5}};
 //       int[][] matrix = {{0, 30}, {5, 10}, {15, 20}};
        int[][] matrix = {{1, 5}, {8, 9}, {8, 9}};
       // int result = new MeetingRooms2().minMeetingRooms(matrix);
        //System.out.println(result);

        System.out.println(Integer.toBinaryString(0x3FF).length());

    }
    //[[2,50],[3,5],[12,13],[14,20],[20,45],,]
}
