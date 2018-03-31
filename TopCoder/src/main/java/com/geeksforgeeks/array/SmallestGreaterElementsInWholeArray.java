package com.geeksforgeeks.array;

import java.util.Arrays;
import java.util.stream.Stream;

import com.geeksforgeeks.tree.BinaryTreeNode;

//https://www.geeksforgeeks.org/smallest-greater-elements-in-whole-array/
public class SmallestGreaterElementsInWholeArray {

    int result[] = null;
    BinaryTreeNode root = null;
    int arr[] = null;
    public void find (int arr[]) {
        this.arr = arr;
        result = new int[arr.length];
        root = new BinaryTreeNode(0);
        Arrays.fill(result, -1);
        for (int i = 1 ; i < arr.length ; i++) {
            recursiveInsert(root, 0, i, arr[i]);
        }
        Stream.of(result).forEach(System.out::print);
    }

    private BinaryTreeNode recursiveInsert(BinaryTreeNode root,
                                           int largeElementIndex,
                                           int arrPos,
                                           int element) {
        if (root == null) {
            if (largeElementIndex != -1) {
                result[arrPos] = arr[largeElementIndex];
            }
            return new BinaryTreeNode(arrPos);
        }
        if (this.arr[root.getData()] > element) {
            if (largeElementIndex != -1) {
                result[arrPos] = arr[root.getData()];
            }  else {
                result[arrPos] = -1;
            }
            root.setLeft(recursiveInsert(root.getLeft(), root.getData(), arrPos, element));
        } else {
            if (result[root.getData()] == -1 || result[root.getData()] > arr[arrPos]) {
                result[root.getData()] = arr[arrPos];
            }
            root.setRight(recursiveInsert(root.getRight(), -1, arrPos, element));
        }
        return root;
    }


    public static void main(String[] args) {
        int arr[] = {6, 3 ,9, 8, 10, 2, 1, 15, 7};
        new SmallestGreaterElementsInWholeArray().find(arr);
    }
}
