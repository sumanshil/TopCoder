package com.geeksforgeeks.advanced;

import java.util.Arrays;

import com.geeksforgeeks.tree.BinaryTreeNode;

//https://www.geeksforgeeks.org/find-lca-in-binary-tree-using-rmq/
public class EulerTraversal {

    private int firstOccurenceIndex[];
    private int firstOccurenceAtIndex[];
    private int levels[];
    private int elements[];
    private int index = 0;

    class StInfo {
        int index;
        int element;
    }

    StInfo stInfo[] = new StInfo[100];

    public void eulerTree(BinaryTreeNode root) {
        firstOccurenceIndex = new int[1000];
        firstOccurenceAtIndex = new int[1000];
        levels = new int[1000];
        elements = new int[1000];

        Arrays.fill(firstOccurenceIndex, -1);
        Arrays.fill(levels, -1);
        Arrays.fill(elements, -1);

        eulerTreeUtil(root, 1);
       // constructStUtil(0, );
    }

    public void query (int element1, int element2) {
        int index1 = firstOccurenceIndex[element1];
        int index2 = firstOccurenceIndex[element2];

        int minLevelIndex = getMinLevelIndex(index1, index2);
        int element = elements[minLevelIndex];
        System.out.println(element);

    }

    private int getMinLevelIndex(int index1, int index2) {
        int retVal = 0;
        int minIndex = Integer.MAX_VALUE;
        for (int i = index1 ; i <= index2 ; i++) {
            if (levels[i] < minIndex){
                minIndex = levels[i];
                retVal = i;
            }
        }
        return retVal;
    }


    private void eulerTreeUtil(BinaryTreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (isLeaf(root)) {
            updateArrays(root);
            System.out.println(root.getData()+" ");
            return;
        } else {
            updateArrays(root);
            System.out.println(root.getData()+" ");
            eulerTreeUtil(root.getLeft(), level + 1);
            updateArrays(root);
            System.out.println(root.getData()+" ");
            eulerTreeUtil(root.getRight(), level + 1);
            updateArrays(root);
            System.out.println(root.getData()+" ");
        }
    }

    private void updateArrays(BinaryTreeNode root) {
        levels[index] = root.getData();
        elements[index] = root.getData();
        if (firstOccurenceIndex[root.getData()] == -1) {
            firstOccurenceIndex[root.getData()] = index;
            firstOccurenceAtIndex[index] = root.getData();
        }
        index++;
    }

    private boolean isLeaf(BinaryTreeNode root) {
        return root.getLeft() == null && root.getRight() == null;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        root.insertInLeft(2);
        root.insertInRight(3);

        root.getLeft().insertInLeft(4);
        root.getLeft().insertInRight(5);

        root.getLeft().getRight().insertInLeft(8);
        root.getLeft().getRight().insertInRight(9);

        root.getRight().insertInLeft(6);
        root.getRight().insertInRight(7);

        EulerTraversal eulerTraversal = new EulerTraversal();
        eulerTraversal.eulerTree(root);
        eulerTraversal.query(4, 9);
    }
}
