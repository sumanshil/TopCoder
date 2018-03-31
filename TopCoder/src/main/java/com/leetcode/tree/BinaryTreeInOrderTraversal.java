package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//https://leetcode.com/problems/binary-tree-inorder-traversal/description/
public class BinaryTreeInOrderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> retVal = new ArrayList<>();
        iterative(root, retVal);
        return retVal;
    }

    public void iterative(TreeNode root, List<Integer> list){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null){
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            list.add(curr.val);
            curr = curr.right;
        }
    }


    private void morrisTraversal(TreeNode root, List<Integer> list) {
        while (root != null) {
            if (root.left == null) {
                list.add(root.val);
                root = root.right;

            } else {
                TreeNode inOrderSuccessor = get(root);
                if (inOrderSuccessor.right == null) {
                    inOrderSuccessor.right = root;
                    root = root.left;
                } else {
                    inOrderSuccessor.right = null;
                    list.add(root.val);
                    root = root.right;
                }
            }
        }
    }

    private TreeNode get(TreeNode root) {
        TreeNode rNode = root.left;
        while (rNode.right != null && rNode.right != root) {
            rNode = rNode.right;
        }
        return rNode;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> list = new BinaryTreeInOrderTraversal().inorderTraversal(root);
        System.out.println(list);
    }

}
