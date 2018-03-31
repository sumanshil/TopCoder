package com.topcoder.problems;

public class Test {

    public static void main(String[] args){
        int[] w = new int[9];
        while(w[8] == 0){
            for(int i=0;i<9;i++) {
                w[i]++;
                if(w[i] == 1)
                    break;
                else
                    w[i] = 0;
            }
            
            for(int i = 0 ; i < 9 ; i++){
                System.out.print(w[i]);
            }
            System.out.println();
        }
    }
}
