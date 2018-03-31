package com.geeksforgeeks.linkedlist;

import sun.awt.image.ImageWatched;

public class MergeKSortedLinkedList1 {

    static class LinkedlistNode {
        int data;
        LinkedlistNode next;

        LinkedlistNode(int data){
            this.data = data;
        }
    }

    static class MinHeap {
        private LinkedlistNode[] arr = null;
        private int currentLastIndex;
        private int maxSize;

        public MinHeap(int size){
            this.maxSize = size;
            arr = new LinkedlistNode[size+1];
            currentLastIndex = 0;
        }

        public void add(LinkedlistNode node){
            if (currentLastIndex > maxSize){
                throw new RuntimeException("Heap is full");
            }
            currentLastIndex ++;
            arr[currentLastIndex] = node;
            upheap(currentLastIndex);
        }

        private void upheap(int currentIndex) {
            if (currentIndex == 1){
                return;
            }

            int parent = currentIndex / 2;
            if (arr[parent].data > arr[currentIndex].data){
                swap(parent, currentIndex);
                upheap(parent);
            }
        }

        private void swap(int parent, int currentIndex) {
            LinkedlistNode temp = arr[parent];
            arr[parent] = arr[currentIndex];
            arr[currentIndex] = temp;
        }

        public LinkedlistNode remove(){
            swap(1, currentLastIndex);
            LinkedlistNode retVal = arr[currentLastIndex];
            currentLastIndex--;
            downHeap(1);
            return retVal;
        }

        private void downHeap(int cureentIndex) {
            if (cureentIndex == currentLastIndex){
                return;
            }

            int leftIndex = 2*cureentIndex;
            int minIndex = cureentIndex;
            if (leftIndex <= currentLastIndex && arr[leftIndex].data < arr[cureentIndex].data){
                minIndex = leftIndex;
            }

            int rightIndex = 2*cureentIndex+1;
            if (rightIndex <= currentLastIndex && arr[rightIndex].data < arr[cureentIndex].data){
                minIndex = rightIndex;
            }

            if (minIndex != cureentIndex){
                swap(minIndex, cureentIndex);
                downHeap(minIndex);
            }
        }

        public boolean isEmpty(){
            return currentLastIndex == 0;
        }

    }

    public static void main(String[] args) {
        LinkedlistNode node1 = new LinkedlistNode(1);
        node1.next = new LinkedlistNode(3);
        node1.next.next = new LinkedlistNode(5);

        LinkedlistNode node2 = new LinkedlistNode(2);
        node2.next = new LinkedlistNode(4);
        node2.next.next = new LinkedlistNode(6);

        LinkedlistNode node3 = new LinkedlistNode(7);
        node3.next = new LinkedlistNode(8);
        node3.next.next = new LinkedlistNode(9);

        LinkedlistNode[] arr = new LinkedlistNode[3];
        arr[0] = node1;
        arr[1] = node2;
        arr[2] = node3;

        MinHeap minHeap = new MinHeap(3);

        for ( int i = 0 ; i < 3 ; i++) {
            minHeap.add(arr[i]);
        }

        LinkedlistNode start;
        LinkedlistNode end;
        start = end = null;

        while (!minHeap.isEmpty()){
            LinkedlistNode minNode = minHeap.remove();
            if (start == null){
                start = minNode;
                end = start;
            } else {
                end.next = minNode;
                end = end.next;
            }
            if (minNode.next != null){
                minHeap.add(minNode.next);
            }
        }

        while(start != null){
            System.out.println(start.data);
            start = start.next;
            System.out.println("==>");
            System.out.println();
        }
    }


}
