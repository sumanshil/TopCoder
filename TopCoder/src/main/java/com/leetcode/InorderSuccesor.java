package com.leetcode;

import java.util.Stack;

import com.leetcode.tree.TreeNode;

import sun.awt.geom.AreaOp;

//https://leetcode.com/problems/inorder-successor-in-bst/
public class InorderSuccesor {
    private TreeNode result = null;
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        //recursive(root, p);
        while (root != null) {
            if (root.val < p.val) {
                root = root.right;
            } else if (root.val > p.val){
                result = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return result;
    }

    private void recursive(TreeNode root, TreeNode p) {
        if (root == null) {
            return;
        }

        if (root.val > p.val) {
            result = root;
            recursive(root.left, p);
        } else if (root.val < p.val){
            recursive(root.right, p);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        //root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        TreeNode result = new InorderSuccesor().inorderSuccessor(root, root);
        System.out.println(result == null ? null : result.val);
    }
}
