package com.heap;

/**
 * Created by sshil on 4/18/2016.
 */
public class MinHeapNode<T extends Object> {
    private Object data;
    private int heapIndex;
    private int heapQualifier;
    private MinHeap<T> minHeap;
    private MinHeapKey<T> minHeapKey;

    public MinHeapNode(MinHeap<T> minHeap, MinHeapKey<T> minHeapKey) {
        this.minHeap = minHeap;
        this.minHeapKey = minHeapKey;
    }

    public MinHeapKey<T> getMinHeapKey() {
        return minHeapKey;
    }

    public void setMinHeapKey(MinHeapKey<T> minHeapKey) {
        this.minHeapKey = minHeapKey;
    }

    public Object getData() {
        return data;
    }

    public MinHeap<T> getMinHeap() {
        return minHeap;
    }

    public void setMinHeap(MinHeap<T> minHeap) {
        this.minHeap = minHeap;
    }

    public void setData(Object data) {
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
