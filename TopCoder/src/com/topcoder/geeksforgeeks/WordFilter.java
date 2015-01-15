package com.topcoder.geeksforgeeks;

public class WordFilter
{
  public static String strip(String search, String buffer)
  {
    for (int n = 0; n < buffer.length(); n++)
    {
      if (buffer.regionMatches(true, n,  search, 0, search.length()))
      {
        String res = strip(search, buffer.substring(0, n) + buffer.substring(n+search.length()));
        return res;
      };
    };
    return buffer;
  };
  
  public static void main(String[] args){
	  String result = strip("StRiNg","ccccstrstrinstringgingcccc");
	  System.out.println(result);
  }
}
