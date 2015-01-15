package com.geeksforgeeks.tree;

import com.topcoder.geeksforgeeks.BinarySearchTree;

public class FindDeepestOddLevelLeafNode {

    /**
     * @param args
     */
    private int best;
    public void findLevel(BinarySearchTree.TreeNode node, int level){
        if (node == null){
            return ;
        } else if (node.left == null && node.right == null){
            if (level %2 != 0){
                if (level > best){
                    best = level;
                }
            }
            return;
        }
        findLevel(node.left, level+1);
        findLevel(node.right, level+1);
    }
//    
//      1
//    /   \
//   2     3
// /      /  \  
//4      5    6
//       \     \
//        7     8
//       /       \
//      9         10
//                /
//               11    
    public static void main(String[] args) {
        BinarySearchTree.TreeNode root = new BinarySearchTree.TreeNode(1);
        root.left = new BinarySearchTree.TreeNode(2);
        root.left.left = new BinarySearchTree.TreeNode(4);
        
        root.right = new BinarySearchTree.TreeNode(3);
        root.right.right = new BinarySearchTree.TreeNode(6);
        root.right.right.right = new BinarySearchTree.TreeNode(8);
        root.right.right.right.right = new BinarySearchTree.TreeNode(10);
        root.right.right.right.right.left = new BinarySearchTree.TreeNode(11);

        root.right.left = new BinarySearchTree.TreeNode(5);
        root.right.left.right = new BinarySearchTree.TreeNode(7);
        root.right.left.right.left = new BinarySearchTree.TreeNode(9);
        FindDeepestOddLevelLeafNode object = new FindDeepestOddLevelLeafNode();
        object.findLevel(root, 1);
        System.out.println(object.best);
        
    }

}
