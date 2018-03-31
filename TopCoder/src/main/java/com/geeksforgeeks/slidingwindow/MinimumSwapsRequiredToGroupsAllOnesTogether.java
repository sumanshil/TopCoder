package com.geeksforgeeks.slidingwindow;

//https://www.geeksforgeeks.org/minimum-swaps-required-group-1s-together/
public class MinimumSwapsRequiredToGroupsAllOnesTogether {

    public static void find (int arr[]) {
        int numberOfOnes[] = new int[arr.length];
        if (arr[0] == 1) {
            numberOfOnes[0] = 1;
        }

        for ( int i = 1 ; i < arr.length ; i++) {
            if (arr[i] == 1) {
                numberOfOnes[i] = numberOfOnes[i-1] + 1;
            } else {
                numberOfOnes[i] = numberOfOnes[i-1];
            }
        }

        int totalNumberOfOnes = numberOfOnes[numberOfOnes.length-1];
        int maxNumberOfOnes = 0;
        for ( int i = totalNumberOfOnes-1 ; i < numberOfOnes.length; i++) {
            if ( i == totalNumberOfOnes-1) {
                maxNumberOfOnes = numberOfOnes[i];
            } else {
                maxNumberOfOnes = Math.max(maxNumberOfOnes, numberOfOnes[i] - numberOfOnes[i-totalNumberOfOnes]);
            }
        }

        System.out.println("Total number of swaps "+ (totalNumberOfOnes - maxNumberOfOnes));
    }

    public static void main(String[] args) {
        int arr[] = {1, 0, 1, 0, 1, 1};
        MinimumSwapsRequiredToGroupsAllOnesTogether.find(arr);
    }
}
