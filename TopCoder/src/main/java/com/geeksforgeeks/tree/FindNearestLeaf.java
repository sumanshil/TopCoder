package com.geeksforgeeks.tree;

//https://www.careercup.com/question?id=5697155539927040
public class FindNearestLeaf {

    static class NodeInfo {
        int distance;
        boolean isLeafNode;

        public NodeInfo(int distance, boolean isLeafNode){
            this.distance = distance;
            this.isLeafNode = isLeafNode;
        }
    }
    private int maxLeafDistanceNonDescendant = Integer.MIN_VALUE;




    private void findNonDescendantMaxDistance(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        if ((root.getLeft() == null && root.getRight() == null)){

        }
    }

}
