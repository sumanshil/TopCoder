package com.geeksforgeeks.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sshil on 12/23/16.
 */
public class PrintAllNodesWithKLeaves {

    static class NodeInfo {
        int leafCount;
        boolean isFound;
        List<Integer> list = new ArrayList<>();

        public NodeInfo(int leafCount, boolean isFound){
            this.leafCount = leafCount;
            this.isFound = isFound;
        }
    }

    public  NodeInfo find(BinaryTreeNode root, int k) {
        if (root == null){
            return null;
        }
        if (root != null && root.getRight() == null && root.getLeft() == null) {
            return new NodeInfo(1, false);
        }

        int leafCount = 0;
        NodeInfo lLeafCount = find(root.getLeft(), k);
        NodeInfo rLeafCount = find(root.getRight(), k);


        if (lLeafCount != null){
            leafCount += lLeafCount.leafCount;
        }

        if (rLeafCount != null){
            leafCount += rLeafCount.leafCount;
        }

        if (leafCount == k){
            NodeInfo nodeInfo =  new NodeInfo(leafCount, true);
            nodeInfo.list.addAll(lLeafCount.list);
            nodeInfo.list.addAll(rLeafCount.list);
            nodeInfo.list.add(root.getData());
            return nodeInfo;
        } else {
            NodeInfo nodeInfo =  new NodeInfo(leafCount, true);
            return nodeInfo;
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.insertInLeft(2);
        root.insertInRight(4);
        root.getLeft().insertInLeft(5);
        root.getLeft().insertInRight(6);
        root.getLeft().getLeft().insertInLeft(9);
        root.getLeft().getLeft().insertInRight(10);

        root.getRight().insertInRight(10);
        root.getRight().insertInLeft(7);

        root.getRight().getLeft().insertInLeft(11);
        root.getRight().getLeft().insertInRight(12);

    }
}
