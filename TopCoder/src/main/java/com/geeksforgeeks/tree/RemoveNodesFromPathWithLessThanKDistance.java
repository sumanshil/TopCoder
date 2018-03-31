package com.geeksforgeeks.tree;

public class RemoveNodesFromPathWithLessThanKDistance extends BaseBinarySearchTree
{
    boolean deleteNode = false;
    public Node delete(Node root, int k)
    {
       Node retVal =  deleteUtil(root,
                                 k,
                                 1);
       return retVal;
    }
    
    
    private Node deleteUtil(Node root,
                            int k,
                            int currentCount)
    {
        if ( root == null )
            return null;
        
        if ( root.left == null
                && root.right == null)
        {
            if ( currentCount >= k)
            {
                deleteNode = false;
                return root;
            }
            else
            {
                deleteNode = true;
                return null;
            }
            
        }
        root.left = deleteUtil(root.left,
                               k,
                               currentCount+1);
        root.right = deleteUtil(root.right,
                                k,
                                currentCount+1);
        
        if ( deleteNode )
        {
            if ( root.left != null
                 || root.right != null )
            {
                // can't delete
                deleteNode = false;
                return root;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return root;
        }
    }


    public static void main(String[] args)
    {
        Node node = new Node(1);
        node.left = new Node(2);
        node.right = new Node(3);
        
        node.left.left = new Node(4);
        node.right.right = new Node(6);
        node.left.left.left = new Node(7);
        node.left.left.left.left = new Node(9);
        
        node.left.right = new Node(5);
        node.left.right.right = new Node(11);
        node.right.right.left = new Node(8);
        node.right.right.left.left = new Node(10);
        Node root = new RemoveNodesFromPathWithLessThanKDistance().delete(node, 5);
        RemoveNodesFromPathWithLessThanKDistance.inOrderPrint(root);
    }

}
