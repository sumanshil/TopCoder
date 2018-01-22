package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/find-bottom-left-tree-value/description/
public class FindBottomLeftTreeValue {

    public int findBottomLeftValue(TreeNode root) {
        int firstValue = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            firstValue = queue.peek().val;
            for ( int i = 0 ; i < size ; i++) {
                TreeNode n = queue.remove();
                if (n.left != null) {
                    queue.add(n.left);
                }
                if (n.right != null) {
                    queue.add(n.right);
                }
            }
        }
        return firstValue;
    }

    public static void main(String[] args) {

    }
}
