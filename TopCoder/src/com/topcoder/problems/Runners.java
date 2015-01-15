package com.topcoder.problems;
////http://community.topcoder.com/stat?c=problem_statement&pm=73&rd=2007
public class Runners {

    public int  closestCheckPoint(int[] param0, int[] param1, int[] param2){
        int minSoFar = Integer.MAX_VALUE;
        int checkPoint = 0;
        for(int i = 0 ; i < param0.length ; i++){
            int i1 = param0[i];
            int i2 = param1[i];
            int i3 = param2[i];
            int max;
            int min;
            if (i1 > i2){
                max = i1;
                min = i2;
            } else {
                max = i2;
                min = i1;
            }
            if (max < i3){
                max = i3;
            }
            if (i3 < min){
                min = i3;
            }
            if ((max- min) <= minSoFar){
                minSoFar = (max - min);
                checkPoint = i;
            }
        }
        return checkPoint;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
//        int[] runner1 = {10,40,90};
//        int[] runner2 = {12,37,87};
//        int[] runner3 = {15,32,88};
        
//        int[] runner1 = {3,4,5,8};
//        int[] runner2 = {3,4,6,8};
//        int[] runner3 = {3,5,7,8};
//        
        //int[] runner1 = {0, 1, 2};
        //int[] runner2 = {7, 8, 9};
        //int[] runner3 = {12,13,17};
        int[] runner1 = {1, 2, 4, 6, 8, 19};
        int[] runner2 = {2, 6, 12, 15, 17, 19};
        int[] runner3 = {2, 0, 0, 0, 0, 20};
        int result = new Runners().closestCheckPoint(runner1, runner2, runner3);
        System.out.println(result);
    }

}
