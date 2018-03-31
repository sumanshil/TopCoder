package com.geeksforgeeks.bitmagic;

public class BinaryRepresentationOfANumber {

    public String  print(int n){
        if (n == 0)
            return "0";
        String sb = null;
        if (n%2 == 0)
            sb = "0";
        else if (n%2 == 1)
            sb = "1";
        return print(n/2)+sb;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        int n = 31;
        String result = new BinaryRepresentationOfANumber().print(n);
        System.out.println(result);
        
        

    }

}
