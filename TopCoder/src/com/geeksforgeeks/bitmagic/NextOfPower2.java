package com.geeksforgeeks.bitmagic;

public class NextOfPower2 {

    public int nextOfPower2(int n){
        int count = 1;
        while(n > 0){
            count = count << 1;
            n = n >> 1;
        }
        return count;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        int result = new NextOfPower2().nextOfPower2(5);
        System.out.println(result);

    }

}
