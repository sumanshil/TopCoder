package com.leetcode;

import com.leetcode.tree.TreeNode;

//https://leetcode.com/problems/count-complete-tree-nodes/
public class CountCompleteTreeNodes {

    public int countNodes(TreeNode root) {

        int height = 0;

        TreeNode tempNode = root;
        while (tempNode != null) {
            height++;
            tempNode = tempNode.left;
        }
        height--;
        int countExceptLast = 0;
        for (int i = 0 ; i < height ; i++) {
            countExceptLast += Math.pow(2, i);
        }

        int countAtH = recursive(root, 0 , height);

        return countAtH + countExceptLast;
    }

    private int recursive(TreeNode root, int currentHeight, int maxHeight) {
        if (root == null) {
            return 0;
        }
        if (currentHeight == maxHeight) {
            return 1;
        }

        int lCount = recursive(root.left, currentHeight + 1, maxHeight);
        int rCount = recursive(root.right, currentHeight + 1, maxHeight);
        return lCount + rCount;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);

        int result = new CountCompleteTreeNodes().countNodes(root);
        System.out.println(result);

    }
}
