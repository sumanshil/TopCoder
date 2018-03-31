package com.geeksforgeeks.tree;

import java.util.LinkedList;
import java.util.List;
//http://www.geeksforgeeks.org/serialize-deserialize-n-ary-tree/
public class SerializeAndDesrializeNaryTree 
{
    static class Node
    {
    	char c;
    	List<Node> list = new LinkedList<Node>();
    	int index = 0;
    	public Node(char c)
    	{
    		this.c = c;
    	}
    	
    	public void addChild(Node node)
    	{
    		list.add(index++, node);
    	}
    }
    
    public Node deserialize(String str)
    {
    	new Node(str.charAt(0));
    	Node root = deserializeRecursive(str);
    	return root;
    }
    
    private int currentIndex = 0;
	private Node deserializeRecursive(String str)
	{
		if (currentIndex >= str.length())
		{
			return null;
		}
		else if (str.charAt(currentIndex) == ')')
		{
			return null;
		}
		
		Node root = new Node(str.charAt(currentIndex));
		while(currentIndex < str.length())
		{
			currentIndex++;
			Node child = deserializeRecursive(str);
			if (child == null)
				break;
			root.addChild(child);
		    
		}			
		return root;
	}
	
	private StringBuffer sb = new StringBuffer();
	public String serialize(Node root)
	{
		serializeRecursive(root);
		return sb.toString();
	}


	private void serializeRecursive(Node root)
	{
		if (root == null)
		{
			return;
		}
		
		sb.append(root.c);
		for(Node node : root.list)
		{
			serializeRecursive(node);
		}
		sb.append(')');
	}


	public static void main(String[] args)
	{
		String str = "ABE)FK)))C)DG)H)I)J)))";
        Node root = new SerializeAndDesrializeNaryTree().deserialize(str);
        String str1 = new SerializeAndDesrializeNaryTree().serialize(root);
        System.out.println(str1);
	}

}
