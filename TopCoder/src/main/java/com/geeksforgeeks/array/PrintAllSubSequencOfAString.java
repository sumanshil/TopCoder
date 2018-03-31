package com.geeksforgeeks.array;

public class PrintAllSubSequencOfAString {

    public void find (String str) {
        int length = str.length();
        int totalSubSequence = (int)Math.pow(2, length)-1;

        for (int i = 1; i <= totalSubSequence ; i++) {
            for ( int j = 0; j < length ; j++) {
                if ( (i & (1 << j)) > 0) {
                    System.out.print(str.charAt(j));
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String str = "abc";
        new PrintAllSubSequencOfAString().find(str);
    }

}
