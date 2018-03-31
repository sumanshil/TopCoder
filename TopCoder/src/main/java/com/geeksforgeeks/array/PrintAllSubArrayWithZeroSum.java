package com.geeksforgeeks.array;

/**
 * Created by sshil on 7/7/2016.
 */
//http://www.geeksforgeeks.org/print-all-subarrays-with-0-sum/
public class PrintAllSubArrayWithZeroSum {
    public static void main(String[] args) {
        for (int i = 0 ; i < 10 ; i++) {
            int randomNum = 100000 + (int) (Math.random() * ((999999 - 100000) + 1));
            System.out.println(randomNum);
        }
    }
}
