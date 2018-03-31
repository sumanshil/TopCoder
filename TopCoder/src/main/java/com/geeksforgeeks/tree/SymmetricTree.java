package com.geeksforgeeks.tree;

//https://leetcode.com/problems/symmetric-tree/description/
public class SymmetricTree {

    public void find (BinaryTreeNode root) {
        boolean result = isSymetric(root.getLeft(), root.getRight());
        System.out.println(result);

    }

    private boolean isSymetric(BinaryTreeNode node1, BinaryTreeNode node2) {
        if (node1 == null && node2 != null) {
            return false;
        }
        if ( node1 != null && node2 == null) {
            return false;
        }

        if ( node1 == null && node2 == null){
            return true;
        }

        if (node1.getData() != node2.getData()) {
            return false;
        }

        return isSymetric(node1.getLeft(), node2.getRight())
                && isSymetric(node1.getRight(), node2.getLeft());
    }

    public static void main(String[] args) {
        /*
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.insertInLeft(2);
        root.insertInRight(2);

        root.getLeft().insertInLeft(3);
        root.getLeft().insertInRight(4);

        root.getRight().insertInLeft(4);
        root.getRight().insertInRight(3);
        new SymmetricTree().find(root);
        */
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.insertInLeft(2);
        root.insertInRight(2);
        root.getLeft().insertInRight(3);
        root.getRight().insertInRight(3);
        new SymmetricTree().find(root);

    }
}
