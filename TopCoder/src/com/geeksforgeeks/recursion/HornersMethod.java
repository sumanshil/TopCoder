package com.geeksforgeeks.recursion;
//http://www.geeksforgeeks.org/horners-method-polynomial-evaluation/
public class HornersMethod 
{
	public void evaluate(int[] poly, int x)
	{
		int result = recursiveUtil(poly, x, poly.length-1, 0);
		System.out.println(result);
	}
	
	public int recursiveUtil(int[] poly, int x, int count, int start)
	{
		if ( count == 0) {
			return poly[start];
		}
		
		int temp = (int)(poly[start]* Math.pow(x, count));
		int result = recursiveUtil(poly, x, count-1, start+1);
		return result + temp;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	    new HornersMethod().evaluate(new int[]{2, -6, 2, -1}, 3);
	    
	}
}
