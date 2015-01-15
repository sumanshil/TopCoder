package com.topcoder.problems.round162;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//http://community.topcoder.com/stat?c=problem_statement&pm=1752&rd=4615
public class PermutationCounter
{
	private long count = 0;
	private List<String> list = new ArrayList<String>(); 
    public long count(String n)
    {
    	List<Integer> nonZeroDigits = new ArrayList<Integer>();
    	for(int i = 0 ; i < n.length() ; i++)
    	{
    		char c= n.charAt(i);
    		if (c > '0'&& c <= '9' )
    		{
    			nonZeroDigits.add(Integer.parseInt(""+c));
    		}
    	}
    	Collections.sort(nonZeroDigits);
    	int[] arr = new int[nonZeroDigits.size()];
    	for(int i = 0 ; i < nonZeroDigits.size() ; i++)
    	{
    		arr[i] = nonZeroDigits.get(i);
    	}
    	
    	int length = arr.length;
    	for(int i = length ; i <= n.length() ; i++)
    	{
    		int numberOfZerosNeeded = i - length;
    		StringBuffer sb = new StringBuffer(i);
    		int j = 0;
    		for(j = 0 ; j < length ; j++)
    		{
    			sb.append(arr[j]);
    		}
    		
    		for(j= 0; j < numberOfZerosNeeded; j++)
    		{
    			sb.append("0");
    		}
    		permuteAndCompute("",
    				         sb.toString(),
    				         n);
    	}
    	return count;
    }
    
    
	private void permuteAndCompute(String originalString,
			                       String leftOverString,
			                       String inputString)
	{
		
		if (originalString.length() > 0 &&
			originalString.charAt(0) == '0')
		{
			return;
		}
//		if (leftOverString.length() > 0
//				&& Long.parseLong(leftOverString) == 0)
//		{
//			if (Long.parseLong(originalString+leftOverString) < Long.parseLong(inputString))
//			{				
//				count++;
//			}
//			list.add(originalString+leftOverString);
//			return;
//		}
//		else
			if (!inputString.intern().equals(originalString.intern())
        	&&!list.contains(originalString)
        	&& (leftOverString.length() == 0
			   || leftOverString == null))
		{
        	list.add(originalString);
        //	System.out.println("Considering "+ originalString);
			Long l1 = Long.parseLong(originalString);
			Long l2 = Long.parseLong(inputString);
			if (l1 < l2)
			{
				count++;
			}
		//	System.out.println("Current count "+count);
			return;
		}
		
		for(int i = 0 ; i < leftOverString.length() ; i++)
		{
			String s1 = originalString+leftOverString.charAt(i);
			String s2 = "";
			int n = leftOverString.length();
			for(int j = 0 ; j < i ; j++)
			{
				s2 = s2+ leftOverString.charAt(j);
			}
			
			for(int j = i+1 ; j < n ; j++)
			{
				s2 = s2 + leftOverString.charAt(j);
			}
			permuteAndCompute(s1,
					          s2,
					          inputString);
		}
	}
	
	public long count1(String n) {
	    if (n.length() == 1) return 0;
	    int[] counts = new int[10];
	    for (int ii = 0; ii < n.length(); ++ii) {
	      counts[n.charAt(ii) - '0']++;
	    }
	    long sum = 0;
	    for (int d = 0; d < n.charAt(0) - '0'; ++d) {
	      if (counts[d] == 0) continue;
	      counts[d]--;
	      sum += countAll(counts, n.length() - 1);
	      counts[d]++;
	    }
	    sum += count(n.substring(1));
	    return sum;
	  }
	  
	  long countAll(int[] counts, int total) {
	    BigInteger rr = BigInteger.valueOf(1);
	    for (int i = 2; i <= total; ++i) {
	      rr = rr.multiply(BigInteger.valueOf(i));
	    }
	    BigInteger dd = BigInteger.valueOf(1);
	    for (int i = 0; i < counts.length; ++i) {
	      for (int j = 2; j <= counts[i]; ++j) {
	        dd = dd.multiply(BigInteger.valueOf(j));
	      }
	    }
	    return rr.divide(dd).longValue();
	  }	
	public static void main(String[] args)
	{
		long result = new PermutationCounter().count("890486052230449607");
		System.out.println(result);
        //System.out.println(Long.parseLong("1111111111111111111111111"));
	}

}
