package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.geeksforgeeks.tree.BinaryTreeNode;

//https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
public class MinDepthOfBinaryTree {

    public int find(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for ( int i = 0 ; i < size ; i ++) {
                TreeNode node = queue.remove();
                if (node.left == null && node.right == null) {
                    return result;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
