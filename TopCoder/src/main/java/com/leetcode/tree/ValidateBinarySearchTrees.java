package com.leetcode.tree;

import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/validate-binary-search-tree/description/
public class ValidateBinarySearchTrees {

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        List<Integer> list = new LinkedList<>();
        recursiveInOrder(root, list);
        int minSoFar = Integer.MAX_VALUE;
        int maxSoFar = Integer.MIN_VALUE;
        int index = -1;
        for ( Integer i : list) {
            index += 1;
            if ( minSoFar == Integer.MAX_VALUE) {
                if (index > 0){
                    return false;
                }
                minSoFar = i;
                maxSoFar = i;
                continue;
            } else if ( i <= minSoFar){
                return false;
            }
            if ( i <= maxSoFar){
                return  false;
            } else {
                maxSoFar = i;
            }
        }
        return true;
    }

    private void recursiveInOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        } else {
            recursiveInOrder(root.left, list);
            list.add(root.val);
            recursiveInOrder(root.right, list);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        boolean result = new ValidateBinarySearchTrees().isValidBST(root);
        System.out.println(result);
    }
}
