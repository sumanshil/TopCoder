package com.geeksforgeeks.dynamicprogramming;
//
public class ZigZag {

	public int longestZigZag(int[] sequence){
	    if(sequence.length == 1) return 1;
	    int[] L = new int[sequence.length];
	    int[] diff = new int[sequence.length-1];
	    int maxLength = 1;
	    boolean maxValue;
	     
	    for(int j =0; j<(sequence.length - 1);j++){
	        diff[j] = sequence[j+1] - sequence[j];
	    }
	    if(diff[0] >= 0) maxValue = true;
	    else maxValue = false;
	     
	    for(int j = 1; j< diff.length;j++){
	        boolean curValue= maxValue;
	        if(diff[j] > 0 ) curValue = true;
	        else if(diff[j]<0) curValue = false;
	        if(curValue != maxValue){
	            maxLength++;
	            maxValue = curValue;
	        }
	    }
	     
	    return maxLength+1;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		int result = new ZigZag().longestZigZag(new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8});
		System.out.println(result);
	}
}
