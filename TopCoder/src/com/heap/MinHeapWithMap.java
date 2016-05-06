package com.heap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sshil on 4/18/2016.
 */

public class MinHeapWithMap<T extends  Object> {
    private Map<MinHeapKey<T>, MinHeapNode<T>> map = new HashMap<>();
    private MinHeap<T> minHeap = null;

    public MinHeapWithMap(int size){
        this.minHeap = new MinHeap<>(size);
    }

    public void update(MinHeapKey<T> key, int update, boolean updateQualifier){
        MinHeapNode<T> minHeapNode;
        boolean doDownHeap = false;
        if (map.containsKey(key)){
            minHeapNode = map.get(key);
            if (updateQualifier) {
                minHeapNode.setHeapQualifier(minHeapNode.getHeapQualifier() + update);
                // qualifier has been increased so the node can go down only
                doDownHeap = true;
            } else {
                if (minHeapNode.getHeapQualifier() > update){
                    // qualifier will decrease. So the node may go up but surely not down
                    doDownHeap = false;
                } else {
                    doDownHeap = true;
                }
                minHeapNode.setHeapQualifier(update);
            }
        } else {
            //
            minHeapNode = setUpNewNode(key, update);
            map.put(key, minHeapNode);
        }
        if (minHeapNode.getHeapIndex() != 0) {
            if (doDownHeap) {
                minHeap.downHeap(minHeapNode.getHeapIndex());
            } else {
                minHeap.upHeap(minHeapNode.getHeapIndex());
            }
        } else if (minHeap.getTop() != null && minHeap.getTop().getHeapQualifier() < minHeapNode.getHeapQualifier()) {
            MinHeapNode top = minHeap.extractMin();
            top.setHeapIndex(0);
            minHeap.insert(minHeapNode);
        } else if (minHeap.isSpaceAvailable()) {
            minHeap.insert(minHeapNode);
        }

    }

    public MinHeapNode<T> get(MinHeapKey<T> minHeapKey){
        return map.get(minHeapKey);
    }

    public boolean contains(MinHeapKey<T> minHeapKey){
        return map.containsKey(minHeapKey);
    }

    private MinHeapNode<T> setUpNewNode(MinHeapKey<T> key, int update) {
        MinHeapNode<T> minHeapNode = new MinHeapNode<>(this.minHeap, key);
        minHeapNode.setData(key.getKey());
        minHeapNode.setHeapQualifier(update);
        return minHeapNode;
    }

    public void update(MinHeapKey<T> minHeapKey){
        update(minHeapKey, 1, true);
    }

    public void remove(MinHeapKey<T> minHeapKey) {
        if (map.containsKey(minHeapKey)){
            MinHeapNode minHeapNode = map.get(minHeapKey);
            int index = minHeapNode.getHeapIndex();
            minHeapNode.setHeapQualifier(Integer.MIN_VALUE);
            this.minHeap.upHeap(index);
            this.minHeap.extractMin();
        }
    }

    public MinHeapNode<T> extract(){
        return minHeap.extractMin();
    }

    public boolean isEmpty(){
        return this.minHeap.isEmpty();
    }

    public static void main(String[] args) {
        String str = "aaa bbb ccc aaa bbb ccc aaa bbb ccc ddd eee fff ggg ddd ddd ddd ddd aaa bbb ccc ccc bbb";
        String[] strArr = str.split("\\s+");
        MinHeapWithMap<String> minHeapWithMap = new MinHeapWithMap<>(3);
        for (int i = 0 ; i < strArr.length ; i++) {
            System.out.println(strArr[i]);
            minHeapWithMap.update(new MinHeapKey<>(strArr[i]));
        }

        minHeapWithMap.minHeap.printContent();
    }
}
