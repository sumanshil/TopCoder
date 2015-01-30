package com.geeksforgeeks.tree;

public class BaseBinarySearchTree
{
   static class Node
   {
       int data;
       Node left;
       Node right;
       public Node(int data)
       {
           this.data = data;
       }
       
       public Node insert(int data)
       {
           Node retVal = insertUtil(this, data);
           return retVal;
       }

    private Node insertUtil(Node node, int data)
    {
        if ( node == null )
        {
            return new Node(data);
        }
        if (node.data > data)
        {
            node.left = new Node(data);
        }
        else
        {
            node.right = new Node(data);
        }
        return node;
    }       
   }
   
   public static void inOrderPrint(Node node)
   {
       if ( node == null )
           return;
       inOrderPrint(node.left);
       System.out.println(node.data);
       inOrderPrint(node.right);       
   }
   
   
   
   
}
