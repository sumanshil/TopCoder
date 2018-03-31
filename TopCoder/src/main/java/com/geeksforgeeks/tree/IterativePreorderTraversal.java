package com.geeksforgeeks.tree;

import java.util.Stack;

//http://www.geeksforgeeks.org/iterative-preorder-traversal/
public class IterativePreorderTraversal {

    public void find (BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode node = stack.pop();
            System.out.println(node.getData());
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(10);
        root.insertInLeft(8);
        root.insertInRight(2);

        root.getLeft().insertInLeft(3);
        root.getLeft().insertInRight(5);

        root.getRight().insertInLeft(2);
        new IterativePreorderTraversal().find(root);
    }

}
