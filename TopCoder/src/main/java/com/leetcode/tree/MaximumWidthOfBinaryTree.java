package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/maximum-width-of-binary-tree/description/
public class MaximumWidthOfBinaryTree {

    public int widthOfBinaryTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int maxWidth = Integer.MIN_VALUE;

        while (!queue.isEmpty() && !allNull(queue)) {
            int size = queue.size();
            TreeNode[] arr = queue.toArray(new TreeNode[0]);

            int last = findNonNullFromLast(arr, size);
            int first = findNonNullFromFirst(arr);
            maxWidth = Math.max(maxWidth, (last - first)+1);
            for ( int i = 0 ; i < size ; i++) {
                TreeNode currentNode = queue.remove();
                if (currentNode != null) {
                    queue.add(currentNode.left);
                    queue.add(currentNode.right);
                } else {
                    queue.add(null);
                    queue.add(null);
                }
            }
        }
        return maxWidth;
    }

    private boolean allNull(Queue<TreeNode> queue) {
        TreeNode[] arr = queue.toArray(new TreeNode[0]);
        for ( int i = 0 ; i < arr.length ; i++) {
            if (arr[i] != null) {
                return false;
            }
        }
        return true;
    }

    private int findNonNullFromFirst(TreeNode[] arr) {
        for ( int i = 0 ; i < arr.length ; i++) {
            if (arr[i] != null) {
                return i;
            }
        }
        return 0;
    }

    private int findNonNullFromLast(TreeNode[] queue, int size) {
        for (int i = size-1; i >= 0 ; i--) {
            if (queue[i] !=  null) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right= new TreeNode(2);

        root.left.left = new TreeNode(5);
        root.right.right = new TreeNode(9);

        root.left.left.left = new TreeNode(6);
        root.right.right.right = new TreeNode(7);
        int maxWidth = new MaximumWidthOfBinaryTree().widthOfBinaryTree(root);
        System.out.println(maxWidth);
    }

}
