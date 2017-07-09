package com.geeksforgeeks.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * Created by sshil on 7/3/17.
 */
//http://www.geeksforgeeks.org/find-sub-array-sum-closest-0/
public class FindTheSubArrayWithSumClosestToZero {

    class Node {
        int sum;
        int index;
        Node(int sum, int index) {
            this.sum = sum;
            this.index = index;
        }
    }

    public void find (int[] arr) {
        Node[] nodeArr = new Node[arr.length];
        nodeArr[0] = new Node(arr[0], 0);
        for (int i = 1 ; i < arr.length ; i++){
            nodeArr[i] = new Node(nodeArr[i-1].sum + arr[i], i);
        }
        Comparator<Node> cmp = Comparator.comparingInt((Node p) -> p.sum);
        Arrays.sort(nodeArr, cmp);
        int min = Integer.MAX_VALUE;
        int startIndex = 0;
        int endIndex = 0;
        for ( int i = 1 ; i < nodeArr.length; i++){
            if (nodeArr[i].sum - nodeArr[i-1].sum < min){
                min = nodeArr[i].sum - nodeArr[i-1].sum;
                startIndex = nodeArr[i].index;
                endIndex = nodeArr[i-1].index;
            }
        }
        System.out.println(startIndex);
        System.out.println(endIndex);
    }

    public static void main(String[] args) {
        int[] arr = {-1, 3, 2, -5, 4};
        new FindTheSubArrayWithSumClosestToZero().find(arr);
    }
}
