package com.geeksforgeeks.array;

//https://www.geeksforgeeks.org/iterative-heap-sort/
public class IterativeHeapSort {

    public void sort(int input[]) {
        buildMaxHeap(input, input.length);
        for (int i = input.length - 1; i > 0 ; i--) {
            swap(input, i, 0);
            buildMaxHeap(input, i);
        }
    }

    private void buildMaxHeap(int[] input, int limit) {
        for (int i = 1 ; i < limit; i++) {
            int parent = (i-1)/2;
            if (input[i] > input[parent]) {
                swap(input, i, parent);
                while (parent > 0) {
                    int newParent = (parent-1)/2;
                    if (input[newParent] < input[parent]) {
                        swap(input, newParent, parent);
                    }
                    parent = newParent;
                }
            }
        }
    }

    private void swap(int[] input, int maxChildIndex, int index) {
        int temp = input[maxChildIndex];
        input[maxChildIndex] = input[index];
        input[index] = temp;
    }

    public static void main(String[] args) {
        int input[] = {10, 20, 15, 7, 9 , 21};
        new IterativeHeapSort().sort(input);
    }
}
