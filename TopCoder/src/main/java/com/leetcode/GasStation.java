package com.leetcode;

//https://leetcode.com/problems/gas-station/
public class GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int tank = 0;
        for (int i = 0 ; i < gas.length ; i++) {
            tank += gas[i];
            tank -= cost[i];
        }

        if (tank < 0) {
            return -1;
        }

        tank = 0;
        int stop = 0;
        for (int i = 0 ; i < gas.length ; i++) {
            tank += gas[i];
            tank -= cost[i];
            if (tank < 0) {
                tank = 0;
                stop = i+1;
            }
        }
        return stop == gas.length ? -1 : stop;
    }


    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        int r = new GasStation().canCompleteCircuit(gas, cost);
        System.out.println(r);
    }
}
