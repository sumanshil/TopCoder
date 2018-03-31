package com.topcoder.problems;

import com.topcoder.geeksforgeeks.BinarySearchTree;
//7. Given a binary tree. A complete path is defined as any path from root to leaf. 
//A k heavy path is a complete path with sum of node values on that path > k, node values can be -ve too.
//Delete all nodes in a tree which do not lie on any k heavy path.
public class KHeavyPath {

    private int currentSum = 0;
    private int targetSum = 190;
    private boolean deleteNode = false;
    /* Let us create following BST
    50
 /     \
30      70
/  \    /  \
20   40  60   80 */    

    
    public BinarySearchTree.TreeNode searchKHeavyPath(BinarySearchTree.TreeNode root){
        if (root == null){
            return null;
        }
        
        currentSum+= root.data;
        if(root.left == null && root.right == null && currentSum < targetSum){
            deleteNode =true;
        } else if (root.left == null && root.right == null && currentSum > targetSum){
            deleteNode =false;
        }
        
        root.left = searchKHeavyPath(root.left);
        root.right = searchKHeavyPath(root.right);
        currentSum -= root.data;
        if (deleteNode){
            return null;
        } else {
            return root;
        }
    }
    
    public void inorder(BinarySearchTree.TreeNode root){
        if (root != null){
            inorder(root.left);
            System.out.println(root.data);
            inorder(root.right);
        }
    }    
    /**
     * @param args
     */
    
    /* Let us create following BST
             50
          /     \
         30      70
        /  \    /  \
       20   40  60   80 */    
    public static void main(String[] args) {
        BinarySearchTree.TreeNode root = new BinarySearchTree.TreeNode(50);
        root.left =  new BinarySearchTree.TreeNode(30);
        root.left.left =new BinarySearchTree.TreeNode(20);
        root.left.right = new BinarySearchTree.TreeNode(40);

        root.right = new BinarySearchTree.TreeNode(70);
        root.right.right = new BinarySearchTree.TreeNode(80);
        root.right.left = new BinarySearchTree.TreeNode(60);
        BinarySearchTree.TreeNode root1 = new KHeavyPath().searchKHeavyPath(root);
        new KHeavyPath().inorder(root1);
    }

}
