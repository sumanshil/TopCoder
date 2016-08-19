package com.hackerrank.dp;

/**
 * Created by sshil on 8/17/2016.
 */
//https://www.hackerrank.com/challenges/mandragora
public class MandragoraForest {

    private static int maxExperience = Integer.MIN_VALUE;

    public static void calculate(int[] arr){
        recursive(arr.length-1, arr, 1, 0);
        System.out.println(maxExperience);
    }

    private static void recursive(int index, int[] arr, int strength, int experience) {
        if (index < 0){
            if (experience > maxExperience){
                maxExperience = experience;
            }
            return;
        }
        // eat it
        recursive(index-1, arr, strength+1, experience);
        recursive(index-1, arr, strength, experience+(strength*arr[index]));
    }


    public static void main(String[] args) {
        int[] arr = {3, 2, 2};
        new MandragoraForest().calculate(arr);
    }
}
