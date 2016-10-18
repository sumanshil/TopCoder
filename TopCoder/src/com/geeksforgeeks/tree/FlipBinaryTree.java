package com.geeksforgeeks.tree;

/**
 * Created by sshil on 10/10/16.
 */
//http://www.geeksforgeeks.org/flip-binary-tree/
public class FlipBinaryTree {

    public void inOrder(BinaryTreeNode root){
        if (root != null) {
            inOrder(root.getLeft());
            System.out.println(root.getData());
            inOrder(root.getRight());
        }
    }

    public void flip(BinaryTreeNode root) {
        BinaryTreeNode newRoot = recursive(root);
        inOrder(newRoot);
    }

    private BinaryTreeNode recursive(BinaryTreeNode root) {
        if (root == null){
            return null;
        }

        BinaryTreeNode rightNode = root.getRight();
        BinaryTreeNode leftNode = root.getLeft();

        BinaryTreeNode newRoot = recursive(root.getLeft());
        if (leftNode != null) {
            leftNode.setRight(root);
            leftNode.setLeft(rightNode);
        }
        root.setLeft(null);
        root.setRight(null);
        return newRoot == null ? leftNode : newRoot;
    }


    public static void main(String[] args) {
        /*
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.insertInLeft(2);
        root.insertInRight(3);
        root.getLeft().insertInLeft(4);
        root.getLeft().insertInRight(5);

        root.getRight().insertInLeft(6);
        root.getRight().insertInRight(7);
        new FlipBinaryTree().flip(root);
        */
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.insertInLeft(2);
        root.insertInRight(3);
        root.getRight().insertInLeft(4);
        root.getRight().insertInRight(5);
        new FlipBinaryTree().flip(root);
    }
}
