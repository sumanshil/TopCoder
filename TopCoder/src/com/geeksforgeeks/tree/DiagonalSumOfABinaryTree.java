package com.geeksforgeeks.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
//http://www.geeksforgeeks.org/diagonal-sum-binary-tree/
public class DiagonalSumOfABinaryTree
{
    static class Node
    {
        int data;
        Node left;
        Node right;
        
        Node(int data)
        {
            this.data = data;
        }
    }
    
    public Integer[] calculate(Node root)
    {
        Queue<Node> queue1 = new LinkedList<Node>();
        Queue<Node> queue2 = new LinkedList<Node>();
        Node temp = root;
        queue1.add(temp);
        Queue<Node> source = queue1;
        Queue<Node> dest = queue2;
        List<Integer> list = new LinkedList<Integer>();
        while(!source.isEmpty()
                || !dest.isEmpty())
        {
            int result = 0;
            while(!source.isEmpty())
            {
                Node n = source.remove();
                result += n.data;
                if (n.left != null)
                {
                    dest.add(n.left);
                }
                if (n.right != null)
                {
                    source.add(n.right);
                }
            }
            
            list.add(result);
            Queue<Node> tempQueue = source;
            source = dest;
            dest = tempQueue;
        }
        
        return (Integer[])list.toArray(new Integer[0]);
    }
    
    public static void main(String[] args)
    {
        Node root = new Node(1);
        
        root.left = new Node(2);
        root.right = new Node(3);
        
        root.left.left = new Node(9);
        root.left.left.right = new Node(10);
        
        root.left.right = new Node(6);
        root.left.right.left = new Node(11);
        
        root.right.left = new Node(4);
        root.right.left.right = new Node(7);
        root.right.left.left = new Node(12);
        
        root.right.right = new Node(5);
        Integer[] list = new DiagonalSumOfABinaryTree().calculate(root);
        for(Integer i : list)
        {
            System.out.println(i);
        }
    }

}
