package com.leetcode;

import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/moving-average-from-data-stream/
public class MovingAverage {
    private int maxSize = 0;
    private int currentSum = 0;

    private List<Integer> list = new LinkedList<>();
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.maxSize = size;
        this.currentSum = 0;
    }

    public double next(int val) {
        if (list.size() == this.maxSize) {
            int oldValue = list.remove(0);
            this.currentSum -= oldValue;
        }
        list.add(val);
        this.currentSum += val;
        return (double) this.currentSum / (double)list.size();
    }

    public static void main(String[] args) {
        MovingAverage movingAverage = new MovingAverage(3);
        System.out.println(movingAverage.next(1));
        System.out.println(movingAverage.next(10));
        System.out.println(movingAverage.next(3));
        System.out.println(movingAverage.next(5));
    }
}
