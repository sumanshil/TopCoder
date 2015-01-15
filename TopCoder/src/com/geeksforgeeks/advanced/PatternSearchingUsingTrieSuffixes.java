package com.geeksforgeeks.advanced;

import java.util.ArrayList;
import java.util.List;

public class PatternSearchingUsingTrieSuffixes 
{
    static class TrieNode
    {
    	char c;
    	List<Integer> indexes;
    	TrieNode[] children = null;
    	public TrieNode(char c)
    	{
    		this();
    		this.c = c;
    		indexes = new ArrayList<Integer>();    		
    	}
    	public TrieNode()
    	{
    		children = new TrieNode[26];
    	}
    }
	
    static class Trie
    {
    	TrieNode root = null;
    	
    	public Trie()
    	{
    		root = new TrieNode();    		
    	}

        public void insert(String text, int index)
        {
             insertUtil(root, text,index);	
        }

		private void insertUtil(TrieNode root,
				                String text,
				                int index)
		{
		    if (text.length() == 0)
		    {
		    	return ;
		    }
			char c = text.charAt(0);
			int  j = c - 'a';
			if (root.children[j] == null)
			{
				root.children[j] = new TrieNode(c);
			}
			else
			{
				root.children[j].c = c;
			}
			//if (text.length()==1)
			{
				root.children[j].indexes.add(index);				
			}
			insertUtil(root.children[j],
					   text.substring(1),
					   index);
		}
		
	    public List<Integer> getIndexes(String pattern)
	    {
	    	return searchUtil(this.root,
	    			   pattern);
	    }

		private List<Integer> searchUtil(TrieNode root,
				                         String pattern)
		{
			if (root == null)
			{
				return null;
			}
			else if (pattern.length() == 1)
			{
				char c = pattern.charAt(0);
				int index = c - 'a';
				if (root.children[index] == null)
				{
					return null;
				}
				return root.children[index].indexes;
   			}
			
			char c = pattern.charAt(0);
			int index = c - 'a';
			return searchUtil(root.children[index],
					pattern.substring(1));
			
		}
    }
    
    
    
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Trie trie = new Trie();
		String text = "geeksforgeeksorg";
		for(int i = 0 ; i < text.length() ; i++)
		{
			trie.insert(text.substring(i), i);	
		}
        
		List<Integer> indexes = trie.getIndexes("or");
		for(Integer i : indexes)
		{
			System.out.println(i);			
		}
	}

}
