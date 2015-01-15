package com.geeksforgeeks.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class SuffixArray 
{
    class Token 
    {
    	String value;
    	int index;
    	public Token(String value, int index)
    	{
    		this.value = value;
    		this.index = index;
    	}
    	
    	public String toString()
    	{
    		return this.value +" " + this.index;
    	}
    }
    
    class StringComparator implements Comparator<Token>
    {

		public int compare(Token o1, Token o2) 
		{
			if ( o1.value.compareTo(o2.value) < 0 )
			{
				return -1;
			}
			else if ( o1.value.compareTo(o2.value) > 0 )
			{
				return 1;
			}
			return 0;
		}


		public Comparator<Token> reversed() {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<Token> thenComparing(Comparator<? super Token> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public <U extends Comparable<? super U>> Comparator<Token> thenComparing(
				Function<? super Token, ? extends U> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public <U> Comparator<Token> thenComparing(
				Function<? super Token, ? extends U> arg0,
				Comparator<? super U> arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<Token> thenComparingDouble(
				ToDoubleFunction<? super Token> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<Token> thenComparingInt(
				ToIntFunction<? super Token> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public Comparator<Token> thenComparingLong(
				ToLongFunction<? super Token> arg0) {
			// TODO Auto-generated method stub
			return null;
		}

    	
    }
	
	public Token[] buildSuffixArray(String input)
	{
		Token[] tokenArray = new Token[input.length()];
		for(int i =0 ; i < input.length() ; i++)
		{
			String subStr = input.substring(i);
			tokenArray[i] = new Token(subStr , i);
		}
		Arrays.sort(tokenArray, new StringComparator());
		return tokenArray;
	}
	
	public int searchToken(String input, String token)
	{
		Token[] array = buildSuffixArray(input);
		int result = findRecursive(array,
				                   input,
				                   token,
				                   0,
				                   array.length-1);
		return result;
	}
	private int findRecursive(Token[] array,
			                  String input,
			                  String token,
			                  int low,
			                  int high) 
	{
		if ( low > high )
		{
			return -1;
		}
		
		int mid = (low + high)/2;
		
		if (token.equals(array[mid].value))
		{
			return array[mid].index;
		}
		
		if ( token.compareTo(array[mid].value) > 0)
		{
			return findRecursive(array, input, token, mid+1, high);
		}
		else
		{
			return findRecursive(array, input, token, low, mid-1);	
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String input = "banana";
		String token = "na";
		int result = new SuffixArray().searchToken(input, token);
		System.out.println(result);

	}

}
