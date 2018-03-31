package com.geeksforgeeks.recursion;

public class RemovAllDuplicates 
{
    public String remove(String str)
    {
    	//String str1 = utility(str, 0);
    	Node n = utility1(str, 0);
    	return n.str;
    }
    
    static class Node
    {
    	String str;
    	int count;
    	public Node(String str, int count)
    	{
    		this.str = str;
    		this.count = count;
    	}
    }
    
    private Node utility1 (String str, int index)
    {
    	if (index == str.length()-1)
    	{
    		return new Node(""+str.charAt(index),0);
    	}
    	char c = str.charAt(index);
    	Node count = utility1(str, index+1);
    	if (count.str.length()>0 && c == count.str.charAt(0))
    	{
    		count.count++;
    		count.str = c+count.str;
    		return count;
    	}
    	else
    	{
    		if (count.count > 0)
    		{
    			if(str.length() > count.count)
    			{
    			    count.str = count.str.substring(count.count+1);
    			}
    			else
    			{
    				count.str=" ";
    			}
    			if (count.str.length() == 0 ||(count.str.length() > 0 && c != count.str.charAt(0)))
    			{
    				count.str = c+count.str;
    			}
    			else if ( count.str.length() >0 && c == count.str.charAt(0))
    			{
    				count.str = count.str.substring(1);
    			}
    		
    			count.count = 0;
    			return count;
    		}
    		else
    		{
    			count.str = c+count.str;
    			return count;
    		}
    	}
    	
    }
    
	private String utility(String str, int index) 
	{
		if (index == str.length()-1)
		{
			return ""+str.charAt(index);
		}
		char c = str.charAt(index);
		String str1 = utility(str, index+1);
		if (str1.length() > 0 && str1.charAt(0) == c)
		{
			return str1;
		}
		else
		{
			return c + str1;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "caaabbbaacdddd";
		String str1 = new RemovAllDuplicates().remove(str);
		System.out.println(str1);

	}

}
