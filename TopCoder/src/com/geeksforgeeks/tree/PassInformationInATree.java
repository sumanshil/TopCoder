package com.geeksforgeeks.tree;
//http://www.geeksforgeeks.org/minimum-iterations-pass-information-nodes-tree/
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PassInformationInATree
{
    static class Node implements Comparable<Node>
    {
        char data;
        Node[] children;
        int index = 0;
        // this contains total number of children in the tree
        int childCount = 0;        
        int height = 0;
        
        public Node(char data,
                    Node[] children)
        {
            this.data = data;
            this.children = children;
        }
        
        public Node getNextChildren()
        {
            if (children == null)
                return null;
            if (index >= children.length)
                return null;
            
            return children[index++];            
        }
        
        public Node()
        {
            
        }

        public Node(char c)
		{
			this.data = c;
		}

		/*
         * (non-Javadoc)
         * @see java.lang.Comparable#compareTo(java.lang.Object)
         * Node with higher child count considered smaller than the Node with lesser children.
         * If children count is same, node with less height is considered smaller.
         * 
         */
		public int compareTo(Node otherNode)
		{
			if (this.childCount < otherNode.childCount)
			{
				return 1;
			}
			else if (this.childCount == otherNode.childCount)
			{
				if (this.height < otherNode.height)
				{
					return -1;
				}
				else
				{
					return 1;
				}
			}
			return -1;
		}
    }

    /* This function will update the childCount and height of a node
     * using recursion 
     */    
    public void countChildrenAndHeight(Node root)
    {
    	int childCount = 0;
    	int height     = 0;
    	Node[] children = root.children;
    	
    	if (children != null)
    	{
	    	for(Node child : children)
	    	{
	    		countChildrenAndHeight(child);
	    		childCount += child.childCount;
	    		if (height < child.height)
	    		{
	    			height = child.height;
	    		}
	    	}
	    	root.childCount = (childCount + children.length);
	    	root.height = height + 1;
	    	Arrays.sort(children);
	    	root.children = children;	    	
    	}
    }
    
    /*
     * Search minimum iteration using BFS
     */
    public int findIterations(Node root)
    {
    	countChildrenAndHeight(root);
        Queue<Node> queue = new LinkedList<Node>();
        int retVal = 0;
        
        queue.add(root);
        
        while(!queue.isEmpty())
        {
            int size = queue.size();
            Node[] nodes = queue.toArray(new Node[]{});
            for(int i =0 ; i < size ; i++)
            {                
                Node node = nodes[i];
                System.out.println("Parent node "+ node);
                Node childNode = node.getNextChildren();
                System.out.println("Child node "+ childNode);
                if (childNode == null)
                {
                    queue.remove(node);
                }
                else
                {
                    queue.add(childNode);
                }
            }

            if (queue.isEmpty())
            {
                break;
            }
            retVal++;            
        }
        return retVal;        
    }
    
    
    public static void main(String[] args)
    {
//        Node root = new Node(); 
//        root.data = 'A';
//        
//        Node children1A = new Node();
//        children1A.data = 'B';
//        Node children2A = new Node();
//        children2A.data = 'C';
//        Node children3A = new Node();
//        children3A.data = 'D';
//        Node children4A = new Node();
//        children4A.data = 'E';
//        Node children5A = new Node();
//        children5A.data = 'F';
//        Node children6A = new Node();
//        children6A.data = 'G';
//        Node[] childArray = {children1A,children2A, children3A, children4A, children5A, children6A};
//        root.children = childArray;
//        
//        Node children11A = new Node();
//        children11A.data = 'H';
//        Node children21A = new Node();
//        children21A.data = 'I';
//        Node children31A = new Node();
//        children31A.data = 'J';
//        childArray = new Node[]{children11A, children21A, children31A};
//        children1A.children = childArray;
//        
//        Node children1_4A = new Node();
//        children1_4A.data = 'K';
//        Node children2_4A = new Node();
//        children2_4A.data = 'L';
//        childArray = new Node[]{children1_4A, children2_4A};
//        children4A.children = childArray;
//        
//        Node children1_6A = new Node();
//        children1_6A.data = 'M';
//        childArray = new Node[]{children1_6A};
//        children6A.children = childArray;
//        
//        Node children1_11A = new Node();
//        children1_11A.data = 'N';
//        Node children2_11A = new Node();
//        children2_11A.data = 'O';
//        childArray = new Node[]{children1_11A, children2_11A};
//        children11A.children = childArray;
//        
//        Node children1_1_4A = new Node();
//        children1_1_4A.data = 'P';
//        childArray = new Node[]{children1_1_4A};
//        children1_4A.children = childArray;
//        
//        Node children1_2_4A = new Node();
//        children1_2_4A.data = 'Q';
//        childArray = new Node[]{children1_2_4A};
//        children2_4A.children = childArray;
//    	Node root = new Node();
//    	root.data = 'A';
//    	Node child1_root = new Node('B');
//    	Node child2_root = new Node('C');
//    	root.children = new Node[]{child1_root, child2_root};
    	
//    	Node A = new Node('A');
//    	Node B = new Node('B');
//    	Node C = new Node('C');
//    	Node D = new Node('D');
//    	Node E = new Node('E');
//    	Node F = new Node('F');
//    	A.children = new Node[]{B};
//    	B.children = new Node[]{C};
//    	C.children = new Node[]{D};
//    	D.children = new Node[]{E};
//    	E.children = new Node[]{F};
    	
    	Node A = new Node('A');
    	Node B = new Node('B');
    	Node C = new Node('C');
    	Node D = new Node('D');
    	Node E = new Node('E');
    	Node F = new Node('F');
    	Node G = new Node('G');
    	Node H = new Node('H');
    	Node I = new Node('I');
    	Node J = new Node('J');
    	Node K = new Node('K');
    	Node L = new Node('L');
    	Node M = new Node('M');
    	Node N = new Node('N');
    	A.children = new Node[]{B, C, D, E, F };    	    	
    	B.children = new Node[]{G};
    	C.children = new Node[]{H};
        D.children = new Node[]{I};  
        E.children = new Node[]{J};   	
        G.children = new Node[]{K};
        H.children = new Node[]{L};
        I.children = new Node[]{M};
        J.children = new Node[]{N};
       int res = new PassInformationInATree().findIterations(A);
       System.out.println(res);
    }

}
