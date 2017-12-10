package com.geeksforgeeks.tree;

import java.util.Stack;

//http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
public class InOrderTreeTraversalWithoutRecursion {

    public void inOrder(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode peek = stack.peek();
            BinaryTreeNode lNode = peek.getLeft();
            while (lNode != null){
                stack.push(lNode);
                lNode = lNode.getLeft();
            }

            BinaryTreeNode pop = stack.pop();
            System.out.println(pop.getData());
            BinaryTreeNode pRight = pop.getRight();
            while (pRight == null && !stack.isEmpty()) {
                pop = stack.pop();
                System.out.println(pop.getData());
                pRight = pop.getRight();
            }
            if (pRight != null){
                stack.push(pRight);
            }
        }
    }


    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.insertInLeft(2);
        root.insertInRight(3);
        root.getLeft().insertInLeft(4);
        root.getLeft().insertInRight(5);
        new InOrderTreeTraversalWithoutRecursion().inOrder(root);
    }

}
