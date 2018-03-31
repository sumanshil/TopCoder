package com.geeksforgeeks.tree;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created by sshil on 5/6/2016.
 */
// http://www.geeksforgeeks.org/print-root-leaf-path-without-using-recursion/
public class PrintRootToLeafWithoutRecursion {

    private Set<BinaryTreeNode> leftExplored = new HashSet<>();
    private Set<BinaryTreeNode> rightExplored = new HashSet<>();

    public void print(BinaryTreeNode root){
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            BinaryTreeNode node = stack.peek();
            if (node.getRight() == null && node.getLeft() == null) {
                stack.stream().map(BinaryTreeNode::getData).forEach(System.out::print);
                stack.pop();
                continue;
            }

            if (!leftExplored.contains(node) && node.getLeft() != null) {
                leftExplored.add(node);
                stack.push(node.getLeft());
                continue;
            }

            if (!rightExplored.contains(node) && node.getRight() != null) {
                rightExplored.add(node);
                stack.push(node.getRight());
                continue;
            }
            stack.pop();
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(6);
        root.setLeft(new BinaryTreeNode(3));
        root.setRight(new BinaryTreeNode(5));

        root.getLeft().setLeft(new BinaryTreeNode(2));
        root.getLeft().setRight(new BinaryTreeNode(5));

        root.getLeft().getRight().setLeft(new BinaryTreeNode(7));
        root.getLeft().getRight().setRight(new BinaryTreeNode(4));

        root.setRight(new BinaryTreeNode(5));
        root.getRight().setRight(new BinaryTreeNode(4));
        new PrintRootToLeafWithoutRecursion().print(root);
    }
}
