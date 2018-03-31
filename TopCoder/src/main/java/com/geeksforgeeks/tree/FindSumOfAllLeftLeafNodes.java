package com.geeksforgeeks.tree;

public class FindSumOfAllLeftLeafNodes extends BaseBinarySearchTree
{
    public int findSum(Node root)
    {
        int sum = findSumRecurive(root, false);
        return sum;
    }
    
    
    private int findSumRecurive(Node root,
                                boolean isLeft)
    {
        if ( root == null )
            return 0;
        
        if (root.left == null
            && root.right == null )
        {
            if ( isLeft)
            {
                return root.data;
            }
            else
            {
                return 0;
            }
        }
        return findSumRecurive(root.left, true)
                + findSumRecurive(root.right, false);
    }


    public static void main(String[] args)
    {
        Node root = new Node(20);
        root.left = new Node(9);
        root.right = new Node(49);

        root.left.left = new Node(5);
        root.left.right = new Node(12);
        root.left.right.right = new Node(15);
        
        root.right.right = new Node(52);
        root.right.left = new Node(23);
        root.right.right.left = new Node(50);
        int result = new FindSumOfAllLeftLeafNodes().findSum(root);
        System.out.println(result);
    }

}
