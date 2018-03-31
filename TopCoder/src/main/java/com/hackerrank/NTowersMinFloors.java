package com.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sshil on 8/15/2016.
 */
//There are N towers with variable number of floors in an ancient
//    city, believed to be the first human colony on Earth. To celebrate
//    the centenary year of its foundation, the queen is willing to
//    build additional floors so that at least m towers are of the same height. The cost of
//    building 1 floor is 1 unit, the queen wants to minimize the cost.
//    Complete the function minFloors , which receives a long array (towers )
//    denoting the heights of the towers and an integer m, denoting the
//    total number of towers which should have same number of floors.
//    The function should return the minimum cost of building new floors.
public class NTowersMinFloors {

    private int minChange = Integer.MAX_VALUE;
    public void find(int[] heightOfTowers, int m){
        recursive(heightOfTowers, 0, m, new ArrayList<>());
        System.out.println(minChange);
    }

    private void recursive(int[] heightOfTowers,
                           int currentIndex,
                           int m,
                           List<Integer> list) {
        if (currentIndex >= heightOfTowers.length){
            if (list.size() == m){
                calculateFloorAndCheck(list);
                return;
            }
            return;
        }
        if (list.size() == m){
            calculateFloorAndCheck(list);
            return;
        }
        recursive(heightOfTowers, currentIndex+1,m, list);
        list.add(heightOfTowers[currentIndex]);
        recursive(heightOfTowers, currentIndex+1, m, list);
        list.remove(list.size()-1);
    }

    private void calculateFloorAndCheck(List<Integer> list) {
        int max = list.stream().mapToInt(e -> e).max().getAsInt();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        list.stream().forEach(e -> atomicInteger.addAndGet(max-e));
        if (atomicInteger.get() < minChange){
            minChange = atomicInteger.get();
        }
    }

    private int findMax(int[] arr, int start, int end){
        int max = Integer.MIN_VALUE;
        for ( int i = start ; i <= end ; i++){
            if (arr[i] > max){
                max = arr[i];
            }
        }
        return max;
    }

    public void findSorted(int[] arr, int k) {
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        for ( int i = 0 ; i < arr.length-k; i++ ) {
            int max = findMax(arr, i, i+k);
            int totalNewFloor = 0;
            for ( int j = i ; j < i+k ; j++){
                totalNewFloor += (max-arr[j]);
            }
            if (totalNewFloor < min){
                min = totalNewFloor;
            }
        }
        System.out.println(min);
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 4,3, 1};
        int minTower = 2;
        new NTowersMinFloors().find(arr, minTower);
    }
}
