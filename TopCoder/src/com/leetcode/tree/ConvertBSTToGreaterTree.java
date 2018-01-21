package com.leetcode.tree;

import java.util.Stack;

//https://leetcode.com/problems/convert-bst-to-greater-tree/description/
public class ConvertBSTToGreaterTree {

    public TreeNode convertBST(TreeNode root) {
        reverseMorrisTraversal(root);
        return root;
    }

    private int recursive(TreeNode root, int greaterSum) {
        if (root == null) {
            return 0;
        }

        int rSum = recursive(root.right, greaterSum);
        int val = root.val;
        root.val = rSum  + greaterSum +val;
        int lSum = recursive(root.left, val+rSum+greaterSum);
        return lSum + val + rSum;
    }

    private int sum = 0;
    public void usingStack(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode node1 = stack.pop();
            while (node1 != null){
                stack.push(node1);
                node1 = node1.right;
            }
            TreeNode top = stack.pop();
            sum += top.val;
            top.val = sum;
            while (true){
                if (stack.isEmpty()) {
                    break;
                }
                top = stack.peek();
                if (top.left != null) {
                    stack.pop();
                    sum += top.val;
                    top.val = sum;
                    stack.push(top.left);
                    break;
                } else {
                    stack.pop();
                    sum += top.val;
                    top.val = sum;
                }
            }
        }
    }

    public void reverseMorrisTraversal(TreeNode root) {
        while (root != null) {
            if (root.right == null) {
                sum += root.val;
                root.val = sum;
                root = root.left;
            } else {
                TreeNode nextNodeReverseInOrder = getNext(root);
                if (nextNodeReverseInOrder.left == root) {
                    nextNodeReverseInOrder.left = null;
                    sum += root.val;
                    root.val = sum;
                    root = root.left;
                } else {
                    nextNodeReverseInOrder.left = root;
                    root = root.right;
                }
            }
        }
    }

    private TreeNode getNext(TreeNode root) {
        TreeNode node = root.right;
        while (node.left != null && node.left != root){
            node = node.left;
        }
        return node;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(0);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(-4);
        root.left.right = new TreeNode(1);
        /*
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(13);
        */
        TreeNode treeNode = new ConvertBSTToGreaterTree().convertBST(root);
        System.out.println(treeNode);
    }

}
