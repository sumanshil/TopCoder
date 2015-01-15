package com.geeksforgeeks.tree;

public class ReverseAlternateLevelInPerfectTree 
{

	static class Node
	{
		int data;
		Node right;
		Node left;
		public Node(int data)
		{
			this.data = data;
		}
		
		public String toString()
		{
			return data+"";
		}
	}
	
	public static Node doTheWork(Node root, int level)
	{
		Node[] parentArray = new Node[1];
		
		
		int currentLevel =  0;
		parentArray[0] = root;
		while(currentLevel < level)
		{
			int j = 0;
			Node[] childArray  = new Node[parentArray.length*2];
			if (currentLevel %2 == 0)
			{
				// reverse the child array only. 
				// No need to reverse the parent array
				//
				for(int i = 0 ; i< parentArray.length ; i++)
				{
					Node n = parentArray[i];
					childArray[j++] = n.left;
					childArray[j++] = n.right;
				}
				childArray = reverse(childArray);
				j = 0;
				for(int i = 0 ; i < parentArray.length ; i++)
				{
					Node n = parentArray[i];
					n.left = childArray[j++];
					n.right = childArray[j++];
				}
			}
			else
			{
				// reverse the parent array and store it in some temp copy
				// populate the child now
				Node[] reverseParent = reverse(parentArray);
				
				for(int i = 0 ; i< reverseParent.length ; i++)
				{
					Node n = reverseParent[i];
					childArray[j++] = n.left;
					childArray[j++] = n.right;
				}
				j = 0;
				parentArray = reverse(parentArray);
				for(int i = 0 ; i < parentArray.length ; i++)
				{
					Node n = parentArray[i];
					n.left = childArray[j++];
					n.right = childArray[j++];
				}				
			}
			
			
//			if (level %2 != 1)
//			{
//				// odd level 
//				parentArray = reverse(parentArray);
//				int k = 0;
//				for(int i = 0 ; i < parentArray.length ; i++)
//				{
//					parentArray[i].left  = childArray[k++];
//					parentArray[i].right = childArray[k++];
//				}
//			}
//			else
//			{
//				// even level. no reverse needed
//				
//			}
			parentArray = childArray;
			currentLevel++;
		}
		return root;
		
 	}
	
	public static void inOrder(Node root)
	{
		if (root == null)
		{
			return;
		}
		inOrder(root.left);
		System.out.println(root.data);
		inOrder(root.right);
	}
	
	
    private static Node[] reverse(Node[] parentArray) 
    {
        int start = 0;
        int end = parentArray.length-1;
        while(start < end)
        {
        	Node temp = parentArray[start];
        	parentArray[start] = parentArray[end];
        	parentArray[end] = temp; 
        	start++;
        	end--;
        }
        return parentArray;
	}



	//	    1
//       /     \
//      2       3
//    /  \     /  \
//   4    5    6     7
//  / \  / \  / \    / \
//8   9 10 11 12 13 14  15 	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        
        root.left.left.left = new Node(8);
        root.left.left.right = new Node(9);

        root.left.right.left = new Node(10);
        root.left.right.right = new Node(11);
        
        root.right.left.left = new Node(12);
        root.right.left.right = new Node(13);
        
        root.right.right.left = new Node(14);
        root.right.right.right = new Node(15);
        Node node1 =  ReverseAlternateLevelInPerfectTree.doTheWork(root, 3);
        ReverseAlternateLevelInPerfectTree.inOrder(node1);
	}

}
