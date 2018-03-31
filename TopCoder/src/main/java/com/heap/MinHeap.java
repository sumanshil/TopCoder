package com.heap;

/**
 * Created by sshil on 4/18/2016.
 */
public class MinHeap<T extends Object>{
    private int maxSize = Integer.MAX_VALUE;
    private int currentIndex = 0;
    private boolean isEmpty = false;
    private MinHeapNode<T>[] arr = null;

    public MinHeapNode<T> getTop(){
        return arr[1];
    }

    public boolean isSpaceAvailable(){
        return currentIndex <= maxSize;
    }

    public MinHeap(int maxSize){
        this.maxSize = maxSize;
        arr = new MinHeapNode[maxSize+1];
        currentIndex = 1;
        isEmpty = true;
    }

    public boolean isEmpty(){
        return isEmpty;
    }

    public void insert(MinHeapNode<T> minHeapNode){
        if (currentIndex <= maxSize){
            arr[currentIndex] = minHeapNode;
            minHeapNode.setHeapIndex(currentIndex);
            upHeap(currentIndex);
            currentIndex++;
            if (isEmpty) {
                isEmpty = false;
            }
        }
    }

    public void upHeap(int currentIndex) {
        if (currentIndex == 0){
            return;
        }

        int parent = currentIndex/2;
        if (arr[parent] != null && arr[parent].getHeapQualifier() > arr[currentIndex].getHeapQualifier() ){
            swap(parent, currentIndex);
            upHeap(parent);
        }
    }

    public MinHeapNode<T> extractMin(){
        if (isEmpty){
            return null;
        }
        MinHeapNode<T> retVal = arr[1];
        if (currentIndex > 1) {
            //arr[1] = arr[--currentIndex];
            swap(1, --currentIndex);
            arr[currentIndex] = null;
            downHeap(1);
        } else {
            currentIndex = 1;
        }
        if (currentIndex == 1){
            isEmpty = true;
        }
        return retVal;
    }

    public void downHeap(int currentIndex) {
        if (currentIndex == 0){
            return;
        }
        if (currentIndex > maxSize){
            return;
        }
        int leftChild = 2*currentIndex;
        int rightChild = 2*currentIndex + 1;
        int minIndex;
        if (leftChild < this.currentIndex &&
            arr[currentIndex].getHeapQualifier() > arr[leftChild].getHeapQualifier()) {
            minIndex = leftChild;
        } else {
            minIndex = currentIndex;
        }

        if (rightChild < this.currentIndex &&
            arr[minIndex].getHeapQualifier() > arr[rightChild].getHeapQualifier()) {
            minIndex = rightChild;
        }
        if (minIndex != currentIndex){
            swap(minIndex, currentIndex);
            downHeap(minIndex);
        }
    }

    public void printContent(){
        for (int i = 1 ; i < currentIndex ; i++){
            System.out.println("===>"+arr[i].getData()+" ===> "+arr[i].getHeapQualifier());
        }
    }

    private void swap(int pos1, int pos2) {
        MinHeapNode temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos1].setHeapIndex(pos1);
        arr[pos2] = temp;
        arr[pos2].setHeapIndex(pos2);
    }

}
