package com.geeksforgeeks.advanced;

public class TernarySearchTree1 
{
    static class Node
    {
    	char c ;
    	Node left ;
    	Node right;
    	Node equal;
    	boolean isWord;
        public Node(char c)
        {
        	this.c = c;
        }
    }
    
    Node root = null;
    public void insert(String word)
    {
    	root = insertUtil(root, word, 0);
    }
    
    
    public boolean search(String word)
    {
    	boolean result = searchTST(this.root,
    			                   word,
    			                   0);
    	return result;
    }
    
	private boolean searchTST(Node root,
			                  String word,
			                  int index)
	{
		 if (root == null)
		     return false;
		 
		 if (word.charAt(index) < root.c)
		     return searchTST(root.left, word, index);
		 
		 else if (word.charAt(index) > root.c)
		     return searchTST(root.right, word, index);
		 
		 else
		 {
		     if (index == word.length()-1)
		         return root.isWord;
		 
		     return searchTST(root.equal, word, index+1);
		 }		
	}


	private Node insertUtil(Node root, String word, int index) 
	{
		if (index >= word.length())
		{
			return null;
		}
		if (root == null)
		{
			root = new Node(word.charAt(index));
		}
		if (root.c < word.charAt(index))
		{
			root.right = insertUtil(root.right, word, index);
		}
		else if (root.c > word.charAt(index))
		{
			root.left  =  insertUtil(root.left, word, index);
		}
		else
		{
			root.equal = insertUtil(root.equal, word, index+1);
			if (index == word.length()-1)
			{
				root.isWord = true;
			}
		}
		return root;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
	    String[] array = {"cat","cats","cdts", "up", "bug"};
        TernarySearchTree1 tst = new TernarySearchTree1();
        for(String str : array)
        {
        	tst.insert(str);
        }
        System.out.println(tst.search("cts"));
	}

}
