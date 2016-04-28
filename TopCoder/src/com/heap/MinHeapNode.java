package com.heap;

/**
 * Created by sshil on 4/18/2016.
 */
public class MinHeapNode {
    private String data;
    private int heapIndex;
    private int heapQualifier;
    private MinHeap minHeap;

    public MinHeapNode(MinHeap minHeap) {
        this.minHeap = minHeap;
    }

    public String getData() {
        return data;
    }

    public MinHeap getMinHeap() {
        return minHeap;
    }

    public void setMinHeap(MinHeap minHeap) {
        this.minHeap = minHeap;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getHeapIndex() {
        return heapIndex;
    }

    public void setHeapIndex(int heapIndex) {
        this.heapIndex = heapIndex;
    }

    public int getHeapQualifier() {
        return heapQualifier;
    }

    public void setHeapQualifier(int heapQualifier) {
        this.heapQualifier = heapQualifier;
        //this.minHeap.downHeap(this.heapIndex);
    }
}
