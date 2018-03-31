package com.geeksforgeeks.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//http://www.geeksforgeeks.org/bottom-view-binary-tree/
public class BottomViewOfBinaryTree
{
    static class Node
    {
        int data;
        Node left;
        Node right;
        int  horizontalLevel;
        int  verticalLevel;
        public Node(int data)
        {
            this.data = data;
        }
    }
     
    Map<Integer, Node> map = new HashMap<Integer, Node>();
    public void calculate(Node root)
    {
        Queue<Node> queue = new LinkedList<Node>();
        root.horizontalLevel = 0;
        root.verticalLevel = 0;
        queue.add(root);
        int minVerticalLevel = Integer.MAX_VALUE;
        int maxVerticalLevel = Integer.MIN_VALUE;
        while(!queue.isEmpty())
        {
            Node node = queue.remove();
            int verticalLevel = node.verticalLevel;
            if (verticalLevel < minVerticalLevel)
            {
                minVerticalLevel = verticalLevel;
            }
            
            if (verticalLevel > maxVerticalLevel)
            {
                maxVerticalLevel = verticalLevel;
            }
            
            Node node1 = map.get(verticalLevel);
            if ( node1 == null)
            {
                map.put(verticalLevel, node);
            }
            else if (node1.horizontalLevel < node.horizontalLevel)
            {
                map.put(verticalLevel, node);
            }
            if (node.right != null)
            {
                node.right.horizontalLevel = node.horizontalLevel+1;
                node.right.verticalLevel = node.verticalLevel+1;
                queue.add(node.right);
            }
            
            if(node.left != null)
            {
                node.left.horizontalLevel = node.horizontalLevel+1;
                node.left.verticalLevel = node.verticalLevel-1;
                queue.add(node.left);                
            }
        }
        
        for(int i = minVerticalLevel ; i <= maxVerticalLevel ; i++)
        {
            System.out.println(map.get(i).data);
        }
        
    }
    
    
    
    
    public static void main(String[] args)
    {
        Node root = new Node(20);
        
        root.left = new Node(8);
        root.right = new Node(22);
        
        root.left.left = new Node(5);
        root.left.right = new Node(3);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(14);

        root.right.right = new Node(25);
        
        new BottomViewOfBinaryTree().calculate(root);
    }

}
