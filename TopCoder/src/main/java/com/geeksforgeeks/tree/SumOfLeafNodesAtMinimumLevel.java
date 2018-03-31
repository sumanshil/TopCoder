package com.geeksforgeeks.tree;

//http://www.geeksforgeeks.org/sum-leaf-nodes-minimum-level/
public class SumOfLeafNodesAtMinimumLevel {

    private int minLevel = Integer.MAX_VALUE;
    public void findSum(BinaryTreeNode root) {
        int result = findSumRecursive(root, 0);
        System.out.println(result);
    }

    public int findSumRecursive(BinaryTreeNode root,  int currentLevel) {
        if (currentLevel > minLevel){
            return 0;
        }
        if (root == null){
            return 0;
        }
        if (root.getLeft() == null && root.getRight() == null){
            if (currentLevel < minLevel){
                minLevel = currentLevel;
                return root.getData();
            }
            if (currentLevel == minLevel){
                return root.getData();
            }
            return 0;
        }

        int leftSum = findSumRecursive(root.getLeft(), currentLevel + 1);
        int rightSum = findSumRecursive(root.getRight(), currentLevel +1);
        return leftSum + rightSum;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.insertInLeft(2);
        root.insertInRight(3);

        root.getLeft().insertInLeft(4);
        root.getLeft().insertInRight(5);
        root.getLeft().getRight().insertInLeft(8);


        root.getRight().insertInLeft(6);
        root.getRight().insertInRight(7);
        root.getRight().getLeft().insertInRight(9);
        new SumOfLeafNodesAtMinimumLevel().findSum(root);
    }

}
