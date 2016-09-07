package com.geeksforgeeks.array;

/**
 * Created by sshil on 8/30/16.
 */
//http://www.geeksforgeeks.org/power-set/
public class PowerSet {

    public void find(String[] strArray){
        int size = strArray.length;
        for (int i = 0 ; i < Math.pow(2, size) ; i++ ) {
            for ( int j = 0 ; j < size ; j++) {
                if ( (i & (1 << j)) > 0 ){
                    System.out.print(strArray[j]);
                }
            }
            System.out.println();
        }

    }
    public static void main(String[] args) {

    }
}
