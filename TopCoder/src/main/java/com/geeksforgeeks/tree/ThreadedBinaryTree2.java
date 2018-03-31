package com.geeksforgeeks.tree;

public class ThreadedBinaryTree2 {

    public void traverse(BinaryTreeNode root) {
        BinaryTreeNode current = root;
        while (current != null) {
            if (current.getLeft() == null) {
                System.out.println(current.getData());
                current = current.getRight();
            } else {

                BinaryTreeNode inOrderPredecessor = getInOrderPrdecessor(current.getLeft(), current);

                if (inOrderPredecessor.getRight() == null){
                    inOrderPredecessor.setRight(current);
                    current = current.getLeft();
                } else {
                    System.out.println(current.getData());
                    inOrderPredecessor.setRight(null);
                    current = current.getRight();
                }
            }
        }
    }

    private BinaryTreeNode getInOrderPrdecessor(BinaryTreeNode node, BinaryTreeNode current) {
        while(node.getRight() != null && node.getRight() != current){
            node = node.getRight();
        }
        return node;
    }

    public static void main(String[] args) {
            BinaryTreeNode root = new BinaryTreeNode(1);
            root.insertInLeft(2);
            root.insertInRight(3);

            root.getLeft().insertInLeft(4);
            root.getLeft().insertInRight(5);

            root.getRight().insertInLeft(6);
            root.getRight().insertInRight(7);

        new ThreadedBinaryTree2().traverse(root);
    }

}
