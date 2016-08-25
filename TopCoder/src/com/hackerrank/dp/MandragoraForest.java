package com.hackerrank.dp;

import java.util.Arrays;

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


    public void dp(int[] arr){
        long total  = Arrays.stream(arr).sum();
        Arrays.sort(arr);
        long maxStrength = 0;
        for ( int i = 0 ; i < arr.length ; i++){
            maxStrength = Math.max(maxStrength, (i+1)*total);
            total -= arr[i];
        }
        System.out.println(maxStrength);

    }


    public static void main(String[] args) {
        int[] arr = {1188729 ,1052940, 9159214 ,1041177, 1109408, 1127712, 708150, 1358977, 769261, 990618, 8711306 ,656084 ,156949, 1188775 ,9153873 ,9345902};
        new MandragoraForest().dp(arr);

    }
}
