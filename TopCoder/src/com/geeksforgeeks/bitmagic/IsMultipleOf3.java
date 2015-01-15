package com.geeksforgeeks.bitmagic;

public class IsMultipleOf3 {

    public boolean isMultipleOf3(int n){
        boolean result = false;
        if (n <0)  n = -n;
        
        if (n == 0)
            return true;
        if ( n == 1)
            return false;
        
        int oddCount = 0;
        int evenCount  = 0;
        while (n > 0){
            if ((n & 1) == 1 ){
                oddCount++;
            }
            
            n = n >> 1;
            if ((n & 1) == 1 ){
                evenCount++;
            }
            n = n >> 1;
        }
        result = isMultipleOf3(Math.abs(evenCount-oddCount));
        return result;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
       boolean result =  new IsMultipleOf3().isMultipleOf3(612);
       System.out.println(result);

    }

}
