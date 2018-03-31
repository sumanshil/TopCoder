package com.geeksforgeeks.strings;

import java.util.ArrayList;
import java.util.List;

public class UkkonensSuffixTree
{

	static class Node
	{
		String content;
		List<Node> children = new ArrayList<Node>();
		boolean isLeaf;
		public Node(String content,
				    boolean isLeaf)
		{
			this.content = content;
			this.isLeaf  = isLeaf;
		}
		
		public Node(String content,
				    boolean isLeaf,
				    List<Node> children)
		{
			this.content = content;
			this.isLeaf  = isLeaf;
			this.children = children;
		}
		
		
		public void addChild(Node child)
		{
			this.children.add(child);
		}
		
		public void setIsLeaf(boolean isLeaf)
		{
			this.isLeaf = isLeaf;
		}
		
		public boolean isValidSuffix(String input)
		{
			return this.content.startsWith(input);			
		}
		
	    public void process(String input)
	    {
	    	boolean processed = false;
	    	for(Node node : children)
	    	{
	    		if (node.isValidSuffix(input))
	    		{
	    			node.processString(input);
	    			processed = true;
	    			break;
	    		}
	    	}
	    	if (!processed)
	    	{
	    		this.addChild(new Node(input, true));
	    	}
	    }
		
		public Node processString(String input)
		{
			if (this.content.startsWith(input, 0))
			{
				// we need to split this node
				if (this.content.length() > input.length())
				{
					int index = 0;
					for(; index < this.content.length()  && index < input.length() ;
						  index++)
					{
						if (this.content.charAt(index) != input.charAt(index))							
						{
							break;
						}
					}
					
                    if (index < this.content.length()
                    	&& index < input.length())
                    {
                    	//split
                    	String newContent = this.content.substring(0, index);
                    	String contentLeftOver = this.content.substring(index);
                    	this.content = newContent;
                    	List<Node> myChildren = this.children;
                    	this.children = null;
                    	this.addChild(new Node(contentLeftOver,
                    			               this.isLeaf,
                    			               myChildren));
                    	
                        if (index < input.length())
                        {
                        	// create a new node
                        	String leftOver = input.substring(index);
                        	this.addChild(new Node(leftOver,
                        			               true));
                        }
                    }
					
				}
				else if (input.length() > this.content.length())
				{
					String inputLeftOver = input.substring(this.content.length());
					this.addChild(new Node(inputLeftOver,
							               true));
				}
			}
			return this;
		}
		
	}
	
	
	public static void main(String[] args)
	{
		String str = "xabxac";
		Node root = new Node("", true);
        for(int i = 0 ; i < str.length(); i++)
        {
        	String currentSubStr = str.substring(i, str.length());
        	root.process(currentSubStr);
        }
        	
	}

}
