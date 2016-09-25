package com.geeksforgeeks.tree;

import java.util.Arrays;

/**
 * Created by sshil on 9/18/16.
 */
//http://www.geeksforgeeks.org/replace-every-element-with-the-least-greater-element-on-its-right/
public class ReplaceEveryElementWithLeastGreaterElementOnRight {

    private BinaryTreeNode root = null;

    private Integer leastMax = -1;

    public void find1(int[] arr) {
        int[] result = new int[arr.length];
        result[arr.length-1] = -1;
        root = new BinaryTreeNode(arr[arr.length-1]);
        for (int i = arr.length-2 ; i >= 0 ; i--) {
            findRecursive(root,  arr[i]);
            result[i] = leastMax;
            leastMax = -1;
        }
        Arrays.stream(result).forEach(System.out::println);
    }

    private BinaryTreeNode findRecursive(BinaryTreeNode root, int element) {
        if (root == null ){
            return new BinaryTreeNode(element);
        }
        if (root.getData() > element){
            leastMax = root.getData();
            root.setLeft(findRecursive(root.getLeft(), element));
        } else {
            root.setRight(findRecursive(root.getRight(), element));
        }
        return root;
    }


    public void find (int[] arr){
        int[] result = new int[arr.length];
        result[result.length-1] = -1;
        int n = arr.length;
        for ( int i = n-2 ; i >=0 ; i--) {
            int max = Integer.MAX_VALUE;
            for ( int j = i+1 ; j < n ; j++) {
                if (arr[j] > arr[i]) {
                    max = Math.min(arr[j], max);
                }
            }
            result[i] = max;
        }

        for ( int i = 0 ; i < result.length ; i++){
            System.out.println(result[i]);
        }
    }

    public static void main(String[] args) {
        int[] arr = {8, 58, 71, 18, 31, 32, 63, 92,
                43, 3, 91, 93, 25, 80, 28};
        new ReplaceEveryElementWithLeastGreaterElementOnRight().find1(arr);
    }
}
