package com.geeksforgeeks.tree;

public class PreOrderTraversalIterative {


    public void traverse (BinaryTreeNode root){

        BinaryTreeNode current = root;
        while (current != null) {
            if (current.getLeft() == null) {
                System.out.println(current.getData());
                current = current.getRight();
            } else {
                BinaryTreeNode successor = getSuccessor(current.getLeft(), current);

                if (successor.getRight() == null){
                    successor.setRight(current);
                    System.out.println(current.getData());
                    current = current.getLeft();
                } else {
                    successor.setRight(null);
                    current = current.getRight();
                }
            }
        }
    }

    private BinaryTreeNode getSuccessor(BinaryTreeNode node, BinaryTreeNode current) {
        while (node.getRight() != null && node.getRight() != current){
            node = node.getRight();
        }
        return node;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(4);
        root.insertInLeft(3);
        root.insertInRight(2);

        root.getLeft().insertInLeft(1);
        root.getLeft().insertInRight(4);

        root.getRight().insertInLeft(5);
        root.getRight().insertInRight(6);

        new PreOrderTraversalIterative().traverse(root);
    }

}
