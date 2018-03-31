package com.leetcode.tree;

import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/
public class TwoElementSumInBST {

    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new LinkedList<>();
        inOrder(root, list);
        int startIndex = 0;
        int lastIndex = list.size()-1;
        boolean found = false;
        while (startIndex < lastIndex) {
            int sum = list.get(startIndex) + list.get(lastIndex);
            if (sum == k) {
                found = true;
                break;
            } else if (sum > k) {
                lastIndex --;
            } else {
                startIndex ++;
            }
        }
        return found;
    }

    private void inOrder(TreeNode root, List<Integer> list) {
        if (root != null) {
            inOrder(root.left, list);
            list.add(root.val);
            inOrder(root.right, list);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);

        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        new TwoElementSumInBST().findTarget(root, 9);
    }
}
