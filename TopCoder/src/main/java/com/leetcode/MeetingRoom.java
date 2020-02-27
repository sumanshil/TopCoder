package com.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/meeting-rooms/
public class MeetingRoom {
    class Pair {
        int low;
        int high;

        Pair(int low, int high) {
            this.low = low;
            this.high = high;
        }
    }
    public boolean canAttendMeetings(int[][] intervals) {
        Comparator<int[]> mycomparator = (a, b) -> {
            if (a[0] < b[0]) {
                return -1;
            } else {
                return 1;
            }
        };

        Arrays.sort(intervals, mycomparator);

        for (int i = 0 ; i < intervals.length-1 ; i++) {
            if (intervals[i][1] > intervals[i+1][0]) {
                return false;
            }
        }
        return true;
    }
    /**
    public boolean canAttendMeetings(int[][] intervals) {
        int low = intervals[0][0];
        int high = intervals[0][1];
        List<Pair> list = new LinkedList<>();
        list.add(new Pair(low, high));

        for (int i = 1 ; i < intervals.length ; i++) {
            int low1 = intervals[i][0];
            int high1 = intervals[i][1];

            for (Pair pair : list) {
                if ((high1 <= pair.low)
                || (low1 >= pair.high)) {
                    continue;
                } else {
                    return false;
                }
            }
            list.add(new Pair(low1, high1));
        }
        return true;
    }
     **/


    public static void main(String[] args) {
        int[][] matrix = {
                {16, 22},
                {28, 45},
                {3,   9},
                {46, 50},
                {13, 14}
        };
        boolean result = new MeetingRoom().canAttendMeetings(matrix);
        System.out.println(result);
    }
}
