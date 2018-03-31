package com.geeksforgeeks.tree;

/**
 * Created by sshil on 9/26/16.
 */
//http://www.geeksforgeeks.org/maximum-consecutive-increasing-path-length-in-binary-tree/
public class MaximumIncreasingConsecutivePathLength {

    public void find(BinaryTreeNode root) {
        findRecursive(root);
        System.out.println(maxDistance);
    }

    private int recursive1(BinaryTreeNode root,  BinaryTreeNode prevNode, int prevLen) {
        if (root == null){
            return prevLen;
        }
        int currentVal = root.getData();

        if (currentVal - prevNode.getData() == 1) {
            return Math.max(recursive1(root.getRight(), root, prevLen+1),
                    recursive1(root.getLeft(), root, prevLen+1));
        }
        int newMaxLength = Math.max(recursive1(root.getLeft(), root, 1),recursive1(root.getRight(), root, 1));
        return Math.max(prevLen, newMaxLength);
    }

    private int maxDistance = Integer.MIN_VALUE;

    private int findRecursive(BinaryTreeNode root) {
        if (root == null){
            return 1;
        }
        int lCount = findRecursive(root.getLeft());
        int rCount = findRecursive(root.getRight());
        if (root.getLeft() != null
                && (root.getLeft().getData() - root.getData()) == 1 ){
            lCount = lCount + 1;
        } else if (root.getLeft() != null &&(root.getLeft().getData() - root.getData()) != 1 ){
            lCount = 1;
        }

        if (root.getRight() != null
                && (root.getRight().getData() - root.getData()) == 1){
            rCount = rCount + 1;
        } else if (root.getRight() != null && (root.getRight().getData() - root.getData()) != 1) {
            rCount = 1;
        }
        maxDistance = Math.max(maxDistance, Math.max(lCount, rCount));
        return Math.max(lCount, rCount);
    }

    public static void main(String[] args) {

        BinaryTreeNode root = new BinaryTreeNode(10);
        root.insertInLeft(11);
        root.insertInRight(9);
        root.getLeft().insertInLeft(13);
        root.getLeft().insertInRight(12);
        root.getRight().insertInLeft(13);
        root.getRight().insertInRight(8);
        new MaximumIncreasingConsecutivePathLength().find(root);

        /*
        BinaryTreeNode root  = new BinaryTreeNode(5);
        root.insertInLeft(8);
        root.insertInRight(11);
        root.getLeft().insertInLeft(9);
        root.getLeft().getLeft().insertInLeft(6);

        root.getRight().insertInRight(10);
        root.getRight().getRight().insertInLeft(15);
        new MaximumIncreasingConsecutivePathLength().find(root);
        */
    }
}
