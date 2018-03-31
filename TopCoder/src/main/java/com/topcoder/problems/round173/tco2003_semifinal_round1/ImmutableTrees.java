package com.topcoder.problems.round173.tco2003_semifinal_round1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//https://community.topcoder.com/stat?c=problem_statement&pm=1961&rd=4706
public class ImmutableTrees {
    static class BinarySerachTreeNode {
        private boolean isDeleted;
        private int data;
        private BinarySerachTreeNode leftNode;
        private BinarySerachTreeNode rightNode;
        private Set<Integer> referencingTrees = new HashSet<>();

        public BinarySerachTreeNode(int data) {
            this.data = data;
        }

        public void markDeleted(){
            this.isDeleted = true;
        }

        public void addReference(int  referencingTree) {
            this.referencingTrees.add(referencingTree);
            if (this.leftNode != null) {
                this.leftNode.addReference(referencingTree);
            }
            if (this.rightNode != null) {
                this.rightNode.addReference(referencingTree);
            }
        }
    }

    private Map<Integer, BinarySerachTreeNode> treeNumberToNodeMap = new HashMap<>();
    private int currentCount = 0;

    private int totalNodeCount = 0;

    public int numNodes(int[] values, int[] trees, int[] garbage) {
        for (int i = 0 ; i < trees.length ; i++) {
            int treeNumber = trees[i];
            if (treeNumber == -1) {
                treeNumberToNodeMap.put(currentCount++,new BinarySerachTreeNode(values[i]));
                totalNodeCount++;
            } else {
                BinarySerachTreeNode originalNode = treeNumberToNodeMap.get(trees[i]);
                BinarySerachTreeNode newRoot = new BinarySerachTreeNode(originalNode.data);
                treeNumberToNodeMap.put(currentCount++,newRoot);
                totalNodeCount++;
                calculateRecursive(originalNode, newRoot, values[i], i );
            }
        }
        Arrays.sort(garbage);
        int result = calculateResult( garbage);
        return result;
    }

    private int calculateResult(int[] garbage) {
        for ( Map.Entry<Integer, BinarySerachTreeNode> entry : treeNumberToNodeMap.entrySet()) {
            int key = entry.getKey();
            for (int i = 0 ; i < garbage.length ; i++) {
                if (key == garbage[i]) {
                    BinarySerachTreeNode node = entry.getValue();
                    bfs(node, garbage);
                }
            }
        }
        return totalNodeCount;
    }

    private void bfs(BinarySerachTreeNode node, int[] garbage) {
        Queue<BinarySerachTreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            BinarySerachTreeNode node1 = queue.remove();
            Set<Integer> referencingTrees = node1.referencingTrees;
            int count = referencingTrees.size();
            for (Integer i : referencingTrees) {
                if (isGarbageCollected(garbage, i)) {
                    count--;
                }
            }
            if (count == 0) {
                node1.markDeleted();
                totalNodeCount--;
            }
            if (node1.leftNode != null && !node1.leftNode.isDeleted) {
                queue.add(node1.leftNode);
            }
            if (node1.rightNode != null && !node1.rightNode.isDeleted) {
                queue.add(node1.rightNode);
            }
        }

    }

    private boolean isGarbageCollected(int[] garbage, int treeNumber) {
        return Arrays.stream(garbage).filter(e -> e == treeNumber).findAny().isPresent();
    }

    private void calculateRecursive(BinarySerachTreeNode originalNode, BinarySerachTreeNode newRoot, int key, int tree) {
        if (originalNode == null) {
            return;
        }

        if (key > originalNode.data) {
            // copy the left reference
            BinarySerachTreeNode lNode = originalNode.leftNode;
            if (lNode != null) {
                newRoot.leftNode = lNode;
                lNode.addReference(tree);
            }
            if (originalNode.rightNode == null){
                newRoot.rightNode = new BinarySerachTreeNode(key);
                totalNodeCount++;
            } else {
                int rData = originalNode.rightNode.data;
                BinarySerachTreeNode newRNode = new BinarySerachTreeNode(rData);
                totalNodeCount++;
                newRoot.rightNode = newRNode;
                calculateRecursive(originalNode.rightNode, newRNode, key, tree);
            }
        } else {
            BinarySerachTreeNode rNode = originalNode.rightNode;
            if (rNode != null) {
                newRoot.rightNode = rNode;
                rNode.addReference(tree);
            }
            if (originalNode.leftNode == null){
                newRoot.leftNode = new BinarySerachTreeNode(key);
                totalNodeCount++;
            } else {
                int lData = originalNode.leftNode.data;
                BinarySerachTreeNode newLNode = new BinarySerachTreeNode(lData);
                totalNodeCount++;
                newRoot.leftNode = newLNode;
                calculateRecursive(originalNode.leftNode, newLNode, key, tree);
            }
        }
    }

    public static void main(String[] args) {
        int values[] = {448, 728, 873, 738, 391, 64, 187, 843, 945, 990, 852, 324, 721, 569, 817, 552, 985, 541, 97, 330, 737, 882, 547, 980, 910, 346, 533, 921, 243, 701, 476, 538, 922, 265, 807, 102, 855, 645, 30, 395};
        int tree[] = {-1, -1, 0, 0, 1, 2, 3, 3, -1, 5, 5, 2, 4, 4, 2, 8, 0, 7, 16, 15, 18, 16, 21, 13, 17, 1, 21, 19, -1, 15, 26, 28, 9, 21, 0, 29, 21, 23, 10, 20};
        int garbage[] = {5, 22, 8, 10, 1, 29, 34, 7, 33, 32, 9, 35, 2, 21, 24, 26, 11, 30, 31, 28, 19, 4, 3, 18, 16, 25, 39, 37, 17, 14, 13, 15, 6, 23};
        int result = new ImmutableTrees().numNodes(values, tree, garbage);
        System.out.println(result);
    }
}
