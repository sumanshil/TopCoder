package com.topcoder.problems.round169;

import java.util.LinkedList;

/**
 * Created by sshil on 11/28/2015.
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=1901&rd=4650
public class FairWorkload {

    private int minDiff = Integer.MAX_VALUE;
    private int maxFinalResultWorkLoad  = 0;

    public int getMostWorkTC(int[] folders, int workers) {
        for ( int i = 1 ; true ; i++){
            if (could(folders, workers, i)){
                return i;
            }
        }
    }

    private boolean could(int[] folders, int workers, int max) {
        int curr = 0;
        for ( int i = 0 ; i < folders.length ; i++) {
            curr += folders[i];
            if ( curr > max ){
                curr = folders[i];
                workers--;
            }
            if (curr > max){
                return  false;
            }
        }
        return  workers > 0;
    }

    public int getMostWork(int[] folders, int workers){

        findRecursive(0,workers, folders, new LinkedList<Integer>());
        return maxFinalResultWorkLoad;
    }

    private void findRecursive(int startIndex,
                               int workers,
                               int[] folders,
                               LinkedList<Integer> list) {
        if (workers == 1) {
            if (startIndex < folders.length) {
                int sum = getSum(folders, startIndex, folders.length);
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for ( int i = 0 ; i < list.size() ; i++){
                    if (min > list.get(i)){
                        min = list.get(i);
                    }
                }
                if (min > sum ){
                    min = sum;
                }
                for ( int i = 0 ; i < list.size() ; i++){
                    if (max < list.get(i)){
                        max = list.get(i);
                    }
                }
                if (max < sum ){
                    max = sum;
                }
                if (max-min < minDiff) {
                    minDiff = max - min;
                    maxFinalResultWorkLoad = max;
                }
            }
            return;
        }
        int limit = folders.length - (workers-1);
        for ( int i = 1 ; i <= limit; i++ ) {
            int sum = getSum(folders,
                             startIndex,
                             startIndex + i);
            list.add(sum);
            findRecursive(startIndex+i,workers-1,folders, list);
            list.remove(list.size()-1);
        }

    }

    private int getSum(int[] folders, int startIndex, int end) {
        int result = 0;
        for ( int i = startIndex ; i < end && i < folders.length; i++) {
            result += folders[i];
        }
        return result;
    }

    public  static void main(String[] args){
        int[] folders = {759, 746, 460, 92, 283, 739, 502, 501, 980, 86, 212, 276, 306, 571, 940};
        int workers = 8;
        int result = new FairWorkload().getMostWork(folders, workers);
        System.out.println(result);
    }
}
