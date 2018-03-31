package com.topcoder.geeksforgeeks;

public class SwapOddBitsWithEvenBits {
	
	public static int method1 (){
		int number = 23;
		int evenBits = number & 0xAAAAAAAA;
		int oddBits = number & 0x55555555;
		evenBits = evenBits >> 1;
		oddBits = oddBits << 1;
		return oddBits | evenBits;
	}
    public static void main(String[] args){
    	int number = 23;
    	int result = method1();
//    	for(int i = 0 ; i < 16; i++){
//    		if ((i %2)== 0){
//    			int shift = 1 << i;
//    			int a1 = number & shift;
//    			a1 = a1 << (1);
//    			result = result | a1;
//    		}else {
//    			int shift = 1 << i;
//    			int a1 = number & shift;
//    			a1 = a1 >> (1);
//    			result = result | a1;
//    		}
//    	}
    	System.out.println(result);
    }
}
