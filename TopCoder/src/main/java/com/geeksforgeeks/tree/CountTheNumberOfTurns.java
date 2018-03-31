package com.geeksforgeeks.tree;

//http://www.geeksforgeeks.org/number-turns-reach-one-node-binary-tree/
public class CountTheNumberOfTurns {

    public void find(BinaryTreeNode root,
                     BinaryTreeNode node1,
                     BinaryTreeNode node2) {
        int result = findRecursive(root,
                root,
                node1,
                node2,
                0,
                false);
        System.out.println(result);
    }

    private int findRecursive(BinaryTreeNode root,
                              BinaryTreeNode originalRoot,
                              BinaryTreeNode node1,
                              BinaryTreeNode node2,
                              int turnCount,
                              boolean isLeft) {
        if (root == null){
            return 0;
        }

        if ((root.getData() == node1.getData()
                || root.getData() == node2.getData()) && root.getData() != originalRoot.getData()){
            return turnCount;
        }
        if ( root.getData() != originalRoot.getData()) {
            int lTurn = findRecursive(root.getLeft(),
                    originalRoot,
                    node1,
                    node2,
                    isLeft ? turnCount : turnCount+1, true);

            int rightTurn = findRecursive(root.getRight(),
                    originalRoot,
                    node1,
                    node2,
                    isLeft? turnCount+1: turnCount, false);
            return lTurn + rightTurn;
        } else {
            int lTurn = findRecursive(root.getLeft(),
                    originalRoot,
                    node1,
                    node2,
                    0
                    , true);

            int rightTurn = findRecursive(root.getRight(),
                    originalRoot,
                    node1,
                    node2,
                    0, false);
            if (lTurn > 0 && rightTurn > 0) {
                return lTurn + rightTurn +1;
            } else {
                return lTurn + rightTurn;
            }
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode rootNode = new BinaryTreeNode(1);
        rootNode.insertInLeft(2);
        rootNode.insertInRight(3);

        rootNode.getLeft().insertInLeft(4);
        rootNode.getLeft().insertInRight(5);
        rootNode.getLeft().getLeft().insertInLeft(8);

        rootNode.getRight().insertInRight(7);
        rootNode.getRight().insertInLeft(6);
        rootNode.getRight().getLeft().insertInLeft(9);
        rootNode.getRight().getLeft().insertInRight(10);

        new CountTheNumberOfTurns().find(rootNode,rootNode.getLeft().getRight(),
                rootNode.getRight().getLeft().getRight());
    }
}
