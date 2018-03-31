package com.geeksforgeeks.tree;

//http://www.geeksforgeeks.org/print-nodes-distance-k-given-node-binary-tree/
public class PrintAllNodesAtDistanceKFromAGivenNode {

    public void printAt(BinaryTreeNode root, int k, int targetNode) {
        recursiveUtil(root, 0, k, targetNode);
    }

    private int recursiveUtil(BinaryTreeNode root, int currentLevel, int k, int targetNode) {
        if (root == null) {
            return 0;
        }
        if (root.getData() == targetNode) {
            printNodesAtKDistance(root, 0, k);
            return currentLevel;
        }
        int leftLevel = recursiveUtil(root.getLeft(), currentLevel+1, k, targetNode);
        int rightLevel = recursiveUtil(root.getRight(), currentLevel+1, k, targetNode);
        if (leftLevel > 0) {
            printNodesAtKDistance(root.getRight(), 0, k-leftLevel-1);
        }
        if (rightLevel > 0){
            printNodesAtKDistance(root.getLeft(), 0, k-rightLevel-1);
        }
        if (leftLevel > 0){
            return  leftLevel + 1;
        }
        if (rightLevel > 0) {
            return  rightLevel + 1;
        }
        return 0;
    }

    private void printNodesAtKDistance(BinaryTreeNode root, int currentLevel, int k) {
        if (root == null) {
            return;
        }
        if (currentLevel == k) {
            System.out.println(root.getData());
            return;
        }
        printNodesAtKDistance(root.getLeft(), currentLevel + 1, k);
        printNodesAtKDistance(root.getRight(), currentLevel + 1, k);
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(20);
        root.insertInLeft(8);
        root.insertInRight(22);

        root.getLeft().insertInLeft(4);
        root.getLeft().insertInRight(12);

        root.getLeft().getRight().insertInLeft(10);
        root.getLeft().getRight().insertInRight(14);
        new PrintAllNodesAtDistanceKFromAGivenNode().printAt(root, 3, 4);

    }

}
