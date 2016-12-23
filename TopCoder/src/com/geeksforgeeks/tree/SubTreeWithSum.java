package com.geeksforgeeks.tree;


/**
 * Created by sshil on 12/23/16.
 */
public class SubTreeWithSum {

    static class NodeInfo {
        int sum;
        boolean isFound;
        NodeInfo(){

        }

        NodeInfo(int sum, boolean isFound){
            this.sum = sum;
            this.isFound = isFound;
        }
    }

    public NodeInfo findSubTree(BinaryTreeNode root, int sum){
        if (root == null) {
            return new NodeInfo(0, false);
        }

        NodeInfo lNodeInfo = findSubTree(root.getLeft(), sum);
        NodeInfo rNodeInfo = findSubTree(root.getRight(), sum);
        if (lNodeInfo.isFound){
            return lNodeInfo;
        } else if (rNodeInfo.isFound) {
            return rNodeInfo;
        } else {
            int leftSum = lNodeInfo.sum;
            int rightSum = rNodeInfo.sum;
            int totalSum = leftSum + rightSum+ root.getData();
            if (totalSum == sum){
                return new NodeInfo(totalSum, true);
            } else {
                return new NodeInfo(totalSum, false);
            }

        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(8);
        root.insertInLeft(5);
        root.insertInRight(4);
        root.getLeft().insertInLeft(9);
        root.getLeft().insertInRight(7);
        root.getLeft().getRight().insertInLeft(1);
        root.getLeft().getRight().insertInRight(12);
        root.getLeft().getRight().getRight().insertInLeft(2);

        root.getRight().insertInRight(11);
        root.getRight().getRight().insertInLeft(3);

        NodeInfo result = new SubTreeWithSum().findSubTree(root, 15);
        System.out.println(result.isFound);
    }
}
