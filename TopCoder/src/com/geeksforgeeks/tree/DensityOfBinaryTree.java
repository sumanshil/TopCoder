package com.geeksforgeeks.tree;

/**
 * Created by sshil on 9/29/16.
 */
//http://www.geeksforgeeks.org/density-of-binary-tree-in-one-traversal/
public class DensityOfBinaryTree {

    public class NodeInfo{
        int height;
        int numberOfNodes;

        public NodeInfo(int height, int numberOfNodes) {
            this.height = height;
            this.numberOfNodes = numberOfNodes;
        }
    }

    public void find(BinaryTreeNode root){
        NodeInfo result = recursive(root);
        System.out.println((float) result.numberOfNodes/(float) result.height);
    }

    private NodeInfo recursive(BinaryTreeNode root) {
        if (root == null) {
            return new NodeInfo(0, 0);
        }
        NodeInfo lNode = recursive(root.getLeft());
        NodeInfo rNode = recursive(root.getRight());
        int height = Math.max(lNode.height, rNode.height);
        return new NodeInfo(height+1, lNode.numberOfNodes+rNode.numberOfNodes+1);
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(10);
        root.insertInLeft(20);
        root.insertInRight(30);
        new DensityOfBinaryTree().find(root);
    }
}
