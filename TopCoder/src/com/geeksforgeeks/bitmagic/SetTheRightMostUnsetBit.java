package com.geeksforgeeks.bitmagic;

/**
 * Created by sshil on 7/2/17.
 */
//http://www.geeksforgeeks.org/set-rightmost-unset-bit/
public class SetTheRightMostUnsetBit {

    public void find (int n) {
        int count = 0;

        while (true) {
            if ( ((1 >> count) & n ) == 0){
                break;
            }
            count++;
        }
        int result = n | (1 << count);
        System.out.println(result);
    }

    public static void main(String[] args) {
        int n = 15;
        new SetTheRightMostUnsetBit().find(n);

    }
}
