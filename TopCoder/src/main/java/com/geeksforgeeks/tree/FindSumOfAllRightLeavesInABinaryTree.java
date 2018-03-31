package com.geeksforgeeks.tree;

//http://www.geeksforgeeks.org/find-sum-right-leaves-given-binary-tree/
public class FindSumOfAllRightLeavesInABinaryTree {

    public void find(BinaryTreeNode root) {
        int result = findSumRecurisve(root, false);
        System.out.println(result);
    }

    private int findSumRecurisve(BinaryTreeNode root, boolean isRight) {
        if (root == null) {
            return 0;
        }

        if (isRight && isLeaf(root)) {
            return root.getData();
        }

        int leftSum = findSumRecurisve(root.getLeft(), false);
        int rightSum = findSumRecurisve(root.getRight(), true);
        return leftSum + rightSum;
    }

    private boolean isLeaf(BinaryTreeNode root) {
        return root.getRight() == null && root.getLeft() == null;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.insertInLeft(2);
        root.insertInRight(3);

        root.getLeft().insertInLeft(4);
        root.getLeft().insertInRight(5);

        root.getLeft().getLeft().insertInRight(2);

        root.getRight().insertInRight(8);
        root.getRight().getRight().insertInRight(7);
        root.getRight().getRight().insertInLeft(6);
        new FindSumOfAllRightLeavesInABinaryTree().find(root);
    }
}
